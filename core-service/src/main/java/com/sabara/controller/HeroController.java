package com.sabara.controller;

import com.sabara.model.entity.Group;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import com.sabara.model.resource.UserResource;
import com.sabara.service.HeroService;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroController {

  private final HeroService service;

  @GetMapping(value = "/{id}")
  ResponseEntity<HeroResource> getHero(@PathVariable long id) {
    return ResponseEntity.ok(Optional.of(service.getHeroById(id))
        .map(hero -> HeroResource.builder()
            .name(hero.getName())
            .fullname(hero.getFullname())
            .placeOfBirth(hero.getPlaceOfBirth())
            .work(hero.getWork())
            .photo(hero.getPhoto())
            .appearance(AppearanceResource.builder()
                .race(hero.getAppearance().getRace())
                .height(hero.getAppearance().getHeight())
                .weight(hero.getAppearance().getWeight())
                .eyes(hero.getAppearance().getEyes())
                .hair(hero.getAppearance().getHair())
                .build())
            .groups(hero.getGroups().stream().map(Group::getName).collect(Collectors.toSet()))
            .build())
        .get());
  }
}

