package com.sabara.service;

import com.sabara.dto.AppearanceDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.dto.PowerstatsDTO;
import com.sabara.dto.SearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "SuperHero", url = "https://superheroapi.com/api/${api_key}")
public interface SuperHeroClient {

  @GetMapping(value = "/{id}")
  Optional<HeroDTO> getHeroById(@PathVariable String id);

  @GetMapping(value = "/{id}/powerstats")
  Optional<PowerstatsDTO> getPowerstats(@PathVariable String id);

  @GetMapping(value = "/{id}/appearance")
  Optional<AppearanceDTO> getAppearance(@PathVariable String id);

  @GetMapping(value = "/search/{name}")
  SearchDTO searchByName(@PathVariable String name);

  @GetMapping(value = "/search/_")
  SearchDTO getAllHeroes();

}

