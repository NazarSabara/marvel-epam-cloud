package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "integration-service", url = "integration-service:8081")
public interface IntegrationServiceClient {

  @GetMapping(value = "/all")
  List<HeroResource> getAllHeroes();

  @GetMapping(value = "/{id}")
  HeroResource getHeroById(@PathVariable Long id);
}
