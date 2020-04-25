package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.HeroResource;
import com.sabara.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sabara.utils.ConversionUtils.HERO_TO_HERO_RESOURCE;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

  private final HeroRepository heroRepository;

  public HeroResource getHeroById(long id) {
    return heroRepository.findById(id).map(HERO_TO_HERO_RESOURCE).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public List<HeroResource> getAllHeroes() {
    return heroRepository.findAll()
            .stream()
            .map(HERO_TO_HERO_RESOURCE)
            .collect(toList());
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Hero addHero(Hero hero) {
    return heroRepository.save(hero);
  }

}
