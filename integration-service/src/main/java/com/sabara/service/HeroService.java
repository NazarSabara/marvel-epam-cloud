package com.sabara.service;

import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.resource.HeroResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sabara.utils.MappingUtils.HERO_MAPPER;
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

    public List<HeroResource> getAllHeroes() {
        return superHeroClient.getAllHeroes()
                .getHeroes()
                .stream()
                .map(HERO_MAPPER)
                .collect(toList());
    }
}
