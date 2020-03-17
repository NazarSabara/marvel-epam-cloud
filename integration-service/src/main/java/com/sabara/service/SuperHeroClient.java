package com.sabara.service;

import com.sabara.dto.AppearanceDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.dto.PowerstatsDTO;
import com.sabara.dto.SearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "SuperHero", url = "${api_url}${api_key}")
public interface SuperHeroClient {

  @GetMapping("/{id}")
  Optional<HeroDTO> getHeroById(@PathVariable long id);

  @GetMapping("/{id}/powerstats")
  Optional<PowerstatsDTO> getPowerstats(@PathVariable long id);

  @GetMapping("/{id}/appearance")
  Optional<AppearanceDTO> getAppearance(@PathVariable long id);

  @GetMapping("/search/{name}")
  SearchDTO searchByName(@PathVariable String name);

  @GetMapping("/search/_")
  SearchDTO getAllHeroes();

}

