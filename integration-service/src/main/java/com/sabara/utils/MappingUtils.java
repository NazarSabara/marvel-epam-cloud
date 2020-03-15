package com.sabara.utils;

import com.sabara.dto.AppearanceDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.dto.PowerstatsDTO;
import com.sabara.model.resource.AppearanceResource;
import com.sabara.model.resource.HeroResource;
import com.sabara.model.resource.PowerstatsResource;

import java.util.Set;
import java.util.function.Function;

import static com.sabara.utils.ConversionUtils.parseHeight;
import static com.sabara.utils.ConversionUtils.parseWeight;

public class MappingUtils {

    public static final Function<AppearanceDTO, AppearanceResource> APPEARANCE_MAPPER =
            appearance ->
                    AppearanceResource.builder()
                            .race(appearance.getRace())
                            .height(parseHeight(appearance.getHeight()))
                            .weight(parseWeight(appearance.getWeight()))
                            .eyes(appearance.getEyeColor())
                            .hair(appearance.getHairColor())
                            .build();
    public static final Function<HeroDTO, HeroResource> HERO_MAPPER =
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
    public static final Function<PowerstatsDTO, PowerstatsResource> POWERSTATS_MAPPER =
            powerstats ->
                    PowerstatsResource.builder()
                            .combat(Integer.valueOf(powerstats.getCombat()))
                            .intelligence(Integer.valueOf(powerstats.getIntelligence()))
                            .durability(Integer.valueOf(powerstats.getDurability()))
                            .power(Integer.valueOf(powerstats.getPower()))
                            .strength(Integer.valueOf(powerstats.getStrength()))
                            .speed(Integer.valueOf(powerstats.getSpeed()))
                            .build();

}
