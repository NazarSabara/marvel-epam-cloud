package com.sabara.controller;

import com.sabara.model.resource.HeroResource;
import com.sabara.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/super-heroes")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroController {

  private final HeroService service;

  @GetMapping("/{id}")
  ResponseEntity<HeroResource> getHero(@PathVariable long id) {
    return ResponseEntity.ok(service.getHeroById(id));
  }

  @GetMapping
  ResponseEntity<List<HeroResource>> getAllHeroes(){
    return ResponseEntity.ok(service.getAllHeroes());
  }
}

