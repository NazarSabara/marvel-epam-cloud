package com.sabara.controller;

import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import com.sabara.model.resource.PowerstatsResource;
import com.sabara.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroController {

  private final HeroService heroService;

  @GetMapping("/{id:\\d+}")
  public HeroResource getHeroById(@PathVariable long id){
    return heroService.getHeroById(id);
  }

  @GetMapping("/{id:\\d+}/appearance")
  public AppearanceResource getAppearance(@PathVariable long id){
    return heroService.getAppearance(id);
  }

  @GetMapping("/{id:\\d+}/powerstats")
  public PowerstatsResource getPowerstats(@PathVariable long id){
    return heroService.getPowerstats(id);
  }

  @GetMapping("/all")
  public List<HeroResource> getAllHeroes() {
    return heroService.getAllHeroes();
  }

  @GetMapping("search/{name}")
  public List<HeroResource> getAllHeroes(@PathVariable String name) {
    return heroService.searchByName(name);
  }

}
