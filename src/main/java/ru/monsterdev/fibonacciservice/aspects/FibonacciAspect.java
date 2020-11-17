package ru.monsterdev.fibonacciservice.aspects;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FibonacciAspect {

  private final MeterRegistry meterRegistry;
  private Timer lastOpDuration;

  public FibonacciAspect(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    lastOpDuration = Timer.builder("fibonacci.lastOpDuration")
        .description("Таймер последней операции")
        .register(meterRegistry);
  }

  @Around("@annotation(io.micrometer.core.annotation.Timed)")
  public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
    Timer.Sample sample = Timer.start(meterRegistry);
    try {
      return joinPoint.proceed();
    } catch (Exception ex) {
      throw ex;
    } finally {
      sample.stop(lastOpDuration);
      log.info("Время выполнения последней операции {} мс", lastOpDuration.max(TimeUnit.MILLISECONDS));
    }
  }
}
