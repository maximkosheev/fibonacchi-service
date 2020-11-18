package ru.monsterdev.fibonacciservice.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.monsterdev.fibonacciservice.controllers.advice.AdviceExceptionHandler;
import ru.monsterdev.fibonacciservice.services.FibonacciService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FibonacciController.class, AdviceExceptionHandler.class})
@WebMvcTest(controllers = {FibonacciController.class})
@WebAppConfiguration
public class FibonacciControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private FibonacciService fibonacciService;


  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
  }

  @Test
  public void getValue_code200() throws Exception {
    when(fibonacciService.getValue(12)).thenReturn(BigInteger.valueOf(144));
    mvc.perform(get("/fibonacci").param("n", "12"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string("144"));
  }

  @Test
  public void getValue_code400() throws Exception {
    mvc.perform(get("/fibonacci").param("n", "-1"))
        .andDo(print())
        .andExpect(result ->
            assertTrue(result.getResolvedException() instanceof IndexOutOfBoundsException));
  }
}
