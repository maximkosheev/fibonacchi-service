package ru.monsterdev.fibonacciservice.services;

import java.math.BigInteger;

/**
 * Сервис работы с последовательностью Фибоначи
 */
public interface FibonacciService {

  /**
   * Возвращает n-ый член последовательности Фибоначи
   * @param n - номер элемента
   * @return член последовательности
   */
  BigInteger getValue(int n);
}
