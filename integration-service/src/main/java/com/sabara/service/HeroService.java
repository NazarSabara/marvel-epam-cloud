package com.sabara.service;

import static com.sabara.utils.ConversionUtils.*;
import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

import com.sabara.dto.AppearanceDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.dto.PowerstatsDTO;
import com.sabara.exception.ResourceNotFoundException;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import com.sabara.model.resource.PowerstatsResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroService {

    private static final Function<AppearanceDTO, AppearanceResource> APPEARANCE_MAPPER =
            appearance ->
                    AppearanceResource.builder()
                            .race(appearance.getRace())
                            .height(
                                    parseHeight(appearance.getHeight()))
                            .weight(
                                    parseWeight(appearance.getWeight()))
                            .eyes(appearance.getEyeColor())
                            .hair(appearance.getHairColor())
                            .build();
    private static final Function<HeroDTO, HeroResource> HERO_MAPPER =
            hero ->
                    HeroResource.builder()
                            .name(hero.getName())
                            .fullname(hero.getName() + hero.getBiography().getFullName())
                            .placeOfBirth(hero.getBiography().getPlaceOfBirth())
                            .work(hero.getWork().getOccupation())
                            .photo(hero.getImage().getUrl())
                            .appearance(APPEARANCE_MAPPER.apply(hero.getAppearance()))
                            .groups(Set.of(hero.getConnections().getGroupAffiliation().split("; ")))
                            .build();
    private static final Function<PowerstatsDTO, PowerstatsResource> POWERSTATS_MAPPER =
            powerstats ->
                    PowerstatsResource.builder()
                            .combat(Integer.valueOf(powerstats.getCombat()))
                            .intelligence(Integer.valueOf(powerstats.getIntelligence()))
                            .durability(Integer.valueOf(powerstats.getDurability()))
                            .power(Integer.valueOf(powerstats.getPower()))
                            .strength(Integer.valueOf(powerstats.getStrength()))
                            .speed(Integer.valueOf(powerstats.getSpeed()))
                            .build();

    private final SuperHeroClient superHeroClient;

    public HeroResource getHeroById(String id) {
        return superHeroClient.getHeroById(id)
                .map(HERO_MAPPER)
                .orElseThrow(() -> new ResourceNotFoundException(parseLong(id)));
    }

    public AppearanceResource getAppearance(String id) {
        return superHeroClient.getAppearance(id)
                .map(APPEARANCE_MAPPER)
                .orElseThrow(() -> new ResourceNotFoundException(parseLong(id)));
    }

    public PowerstatsResource getPowerstats(String id){
        return superHeroClient.getPowerstats(id)
                .map(POWERSTATS_MAPPER)
                .orElseThrow(() -> new ResourceNotFoundException(parseLong(id)));
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
