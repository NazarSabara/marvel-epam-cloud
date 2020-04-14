package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.entity.Group;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import com.sabara.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

  private final HeroRepository heroRepository;

  public HeroResource getHeroById(long id) {
    return heroRepository.findById(id).map(hero -> HeroResource.builder()
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
        .groups(hero.getGroups().stream().map(Group::getName).collect(toSet()))
        .build()).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Hero addHero(Hero hero) {
    return heroRepository.save(hero);
  }
}
