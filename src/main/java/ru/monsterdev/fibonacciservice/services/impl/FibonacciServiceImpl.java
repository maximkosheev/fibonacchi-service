package ru.monsterdev.fibonacciservice.services.impl;

import io.micrometer.core.annotation.Timed;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.monsterdev.fibonacciservice.services.FibonacciService;

@Slf4j
@Service
public class FibonacciServiceImpl implements FibonacciService {

  @Timed
  @Override
  public BigInteger getValue(int n) {
    BigInteger prevPrev = BigInteger.ZERO;
    BigInteger prev = BigInteger.ONE;
    BigInteger curr = BigInteger.ZERO;

    if (n <= 0) {
      return BigInteger.ZERO;
    }
    if (n == 1) {
      return BigInteger.ONE;
    }
    for (long nI = 2; nI <= n; nI++) {
      curr = prev.add(prevPrev);
      prevPrev = prev;
      prev = curr;
    }
    return curr;
  }
}
