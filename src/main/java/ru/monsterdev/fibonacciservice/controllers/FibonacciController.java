package ru.monsterdev.fibonacciservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.monsterdev.fibonacciservice.services.FibonacciService;

@Slf4j
@RestController
@RequestMapping(value = "/fibonacci")
@RequiredArgsConstructor
public class FibonacciController {

  private final FibonacciService fibonacciService;

  @GetMapping()
  @ApiOperation(value = "Возвращает N-ый член последовательности Фибоначи")
  public BigInteger getValue(
      @ApiParam(name = "n", value = "N-ый член последовательности Фибоначи", required = true, example = "12")
      @RequestParam(name = "n") Integer n
  ) {
    if (n < 0) {
      throw new IndexOutOfBoundsException("Параметр n не может быть меньше 0");
    }
    return fibonacciService.getValue(n);
  }
}
