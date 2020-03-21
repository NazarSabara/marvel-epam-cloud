package com.sabara.service;

import com.sabara.dto.HeroDTO;
import com.sabara.dto.SearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "SuperHero", url = "${api_url}${api_key}")
public interface SuperHeroClient {

  @GetMapping("/{id}")
  Optional<HeroDTO> getHeroById(@PathVariable long id);

  @GetMapping("/search/_")
  SearchDTO getAllHeroes();

}

