package com.sabara.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sabara.model.entity.Hero;
import java.util.List;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SuperHero", url = "https://superheroapi.com/api/${api_key}")
public interface SuperHeroClient {

  @GetMapping(value = "/search/_")
  JsonNode getAllHeroes();

  @GetMapping(value = "/{id}")
  Optional<Hero> getHeroById(@PathVariable String id);
}
