package com.sabara.service;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final Function<Hero, HeroResource> HERO_MAPPER =
      hero ->
          HeroResource.builder()
              .name(hero.getName())
              .fullname(hero.getBiography().getFullName())
              .placeOfBirth(hero.getBiography().getPlaceOfBirth())
              .work(hero.getWork().getOccupation())
              .photo(hero.getImage().get("url"))
              .appearance(
                  AppearanceResource.builder()
                      .race(hero.getAppearance().getRace())
                      .height(
                          hero.getAppearance().getHeight().stream().skip(1).findFirst().orElse("-"))
                      .weight(
                          hero.getAppearance().getWeight().stream().skip(1).findFirst().orElse("-"))
                      .eyes(hero.getAppearance().getEyeColor())
                      .hair(hero.getAppearance().getHairColor())
                      .build())
              .groups(Set.of(hero.getConnections().getGroupAffiliation().split("; ")))
              .build();

  private final SuperHeroClient superHeroClient;

  public List<HeroResource> getAllHeroes() {
    return MAPPER
        .convertValue(
            superHeroClient.getAllHeroes().get("results"), new TypeReference<List<Hero>>() {})
        .stream()
        .map(HERO_MAPPER)
        .collect(toList());
  }

  public HeroResource getHeroById(String id) {
    final Optional<Hero> result =
        superHeroClient.getHeroById(id).filter(hero -> isNotBlank(hero.getId()));

    return result.map(HERO_MAPPER).orElseThrow(() -> new ResourceNotFoundException(parseLong(id)));
  }
}
