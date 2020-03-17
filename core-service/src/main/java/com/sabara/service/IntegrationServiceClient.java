package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "integration-service", url = "integration-service:8081", fallbackFactory = IntegrationServiceFallbackFactory.class )
public interface IntegrationServiceClient {

  @GetMapping("/{id}")
  Optional<HeroResource> getHeroById(@PathVariable Long id);

  @GetMapping("/all")
  List<HeroResource> getAllHeroes();
}
