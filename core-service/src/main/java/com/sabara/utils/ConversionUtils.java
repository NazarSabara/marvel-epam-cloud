package com.sabara.utils;

import com.sabara.model.entity.Group;
import com.sabara.model.entity.Hero;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;

import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

public class ConversionUtils {

    public static Function<Hero, HeroResource> HERO_TO_HERO_RESOURCE = hero -> HeroResource.builder()
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
        .build();
}
