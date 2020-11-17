package ru.monsterdev.fibonacciservice.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.monsterdev.fibonacciservice.services.impl.FibonacciServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FibonacciServiceImpl.class})
class FibonacciServiceTest {

  @Autowired
  private FibonacciService fibonacciService;

  @Test
  void getValue() {
    assertEquals(BigInteger.ZERO, fibonacciService.getValue(-12));
    assertEquals(BigInteger.ZERO, fibonacciService.getValue(-1));
    assertEquals(BigInteger.ZERO, fibonacciService.getValue(0));
    assertEquals(BigInteger.ONE, fibonacciService.getValue(1));
    assertEquals(BigInteger.valueOf(144), fibonacciService.getValue(12));
  }
}