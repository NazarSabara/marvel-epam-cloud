package com.sabara.controller;

import com.sabara.model.resource.HeroResource;
import com.sabara.service.HeroService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroController {

  private final HeroService heroService;

  @GetMapping(value = "/all")
  public List<HeroResource> getAllHeroes() {
    return heroService.getAllHeroes();
  }

  @GetMapping(value = "/{id}")
  public HeroResource getHeroById(@PathVariable String id){
    return heroService.getHeroById(id);
  }
}
