package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "core-service", url = "core-service:8081")
@RequestMapping("/super-heroes")
public interface CoreServiceClient {

  @GetMapping("/")
  List<HeroResource> getAllHeroes();

  @PutMapping("/sync")
  void updateHeroes(@RequestBody List<HeroResource> heroesToUpdate);
}
