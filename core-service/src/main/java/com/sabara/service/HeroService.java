package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.HeroResource;
import com.sabara.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

  private final HeroRepository heroRepository;
  private final ModelMapper modelMapper;

  public HeroResource getHeroById(long id) {
    return heroRepository
            .findById(id)
            .map(hero -> modelMapper.map(hero, HeroResource.class))
            .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public List<HeroResource> getAllHeroes() {
    return heroRepository.findAll()
            .stream()
            .map(hero -> modelMapper.map(hero, HeroResource.class))
            .collect(toList());
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Hero addHero(Hero hero) {
    return heroRepository.save(hero);
  }

}
