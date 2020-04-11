package com.sabara.service;

import com.sabara.model.resource.HeroResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

import static com.sabara.utils.MappingUtils.HERO_MAPPER;
import static java.util.List.copyOf;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SyncService {

    private final CoreServiceClient coreServiceClient;
    private final SuperHeroClient superHeroClient;

    public void sync(){
        Map<String, HeroResource> expected = superHeroClient.getAllHeroes()
                .getHeroes()
                .stream()
                .map(HERO_MAPPER)
                .collect(toMap(HeroResource::getFullname, Function.identity()));;
        Map<String, HeroResource> actual = coreServiceClient.getAllHeroes()
                .stream()
                .collect(toMap(HeroResource::getFullname, Function.identity()));

        actual.forEach((key, value) -> expected.merge(key, value, (v1, v2) -> v1));

        coreServiceClient.updateHeroes(copyOf(actual.values()));
    }
}
