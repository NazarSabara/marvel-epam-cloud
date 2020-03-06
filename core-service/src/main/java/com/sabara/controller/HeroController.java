package com.sabara.controller;

import com.sabara.model.resource.HeroResource;
import com.sabara.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroController {

  private final HeroService service;

  @GetMapping(value = "/{id}")
  ResponseEntity<HeroResource> getHero(@PathVariable long id) {
    return ResponseEntity.ok(service.getHeroById(id));
  }
}

