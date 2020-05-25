package com.sabara.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfiguration {

  @Value("${statistic-service.baseUrl}")
  private String statisticServiceBaseUrl;

  @Bean
  public WebClient statisticServiceClient() {
    return WebClient.builder().baseUrl(statisticServiceBaseUrl).build();
  }
}
