package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import com.sabara.model.resource.PowerstatsResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sabara.utils.MappingUtils.*;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

    private final SuperHeroClient superHeroClient;

    public HeroResource getHeroById(Long id) {
        return superHeroClient.getHeroById(id)
                .map(HERO_MAPPER)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public AppearanceResource getAppearance(Long id) {
        return superHeroClient.getAppearance(id)
                .map(APPEARANCE_MAPPER)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public PowerstatsResource getPowerstats(Long id){
        return superHeroClient.getPowerstats(id)
                .map(POWERSTATS_MAPPER)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<HeroResource> getAllHeroes() {
        return superHeroClient.getAllHeroes()
                .getHeroes()
                .stream()
                .map(HERO_MAPPER)
                .collect(toList());
    }

    public List<HeroResource> searchByName(String name){
        return superHeroClient.searchByName(name)
                .getHeroes()
                .stream()
                .map(HERO_MAPPER)
                .collect(toList());
    }
}
