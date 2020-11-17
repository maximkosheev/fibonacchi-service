package ru.monsterdev.fibonacciservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .tags(new Tag("WITHOUT_PRIVILIGES", "Методы с доступом без привилегий"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("ru.monsterdev.fibonacciservice.controllers"))
        .build();
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder
        .builder()
        .filter(true)
        .maxDisplayedTags(0)
        .build();
  }
}
