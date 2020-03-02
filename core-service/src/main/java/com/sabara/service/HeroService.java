package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.entity.Hero;
import com.sabara.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

  private final HeroRepository heroRepository;

  public Hero getHeroById(long id) {
    return heroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Hero addHero(Hero hero){
    return heroRepository.save(hero);
  }
}
