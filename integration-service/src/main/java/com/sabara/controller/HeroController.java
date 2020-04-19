package com.sabara.controller;

import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import com.sabara.model.resource.PowerstatsResource;
import com.sabara.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @CrossOrigin(origins = "${ui_origin}")
  @GetMapping("/")
  public List<HeroResource> getAllHeroes() {
    return heroService.getAllHeroes();
  }

}
