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

  @GetMapping(value = "/{id}")
  public HeroResource getHeroById(@PathVariable String id){
    return heroService.getHeroById(id);
  }

  @GetMapping(value = "/{id}/appearance")
  public AppearanceResource getAppearance(@PathVariable String id){
    return heroService.getAppearance(id);
  }

  @GetMapping(value = "/{id}/powerstats")
  public PowerstatsResource getPowerstats(@PathVariable String id){
    return heroService.getPowerstats(id);
  }

  @GetMapping(value = "search/all")
  public List<HeroResource> getAllHeroes() {
    return heroService.getAllHeroes();
  }

  @GetMapping(value = "search/{name}")
  public List<HeroResource> getAllHeroes(@PathVariable String name) {
    return heroService.searchByName(name);
  }

}
