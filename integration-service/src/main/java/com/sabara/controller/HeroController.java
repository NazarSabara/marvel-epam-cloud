package com.sabara.controller;

import com.sabara.model.resource.HeroResource;
import com.sabara.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroController {

  private final HeroService heroService;

  @GetMapping("/{id}")
  public HeroResource getHeroById(@PathVariable long id){
    return heroService.getHeroById(id);
  }

  @GetMapping
  public List<HeroResource> getAllHeroes() {
    return heroService.getAllHeroes();
  }

}
