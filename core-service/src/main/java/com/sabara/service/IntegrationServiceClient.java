package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "integration-service", url = "integration-service:8081")
public interface IntegrationServiceClient {

  @GetMapping(value = "/{id}")
  HeroResource getHeroById(@PathVariable Long id);

  @GetMapping(value = "/search/all")
  List<HeroResource> getAllHeroes();
}
