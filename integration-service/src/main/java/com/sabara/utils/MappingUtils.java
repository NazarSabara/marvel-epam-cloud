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
import static java.lang.Integer.parseInt;

public class MappingUtils {

    private static int DEFAULT_POWERSTAT_VALUE = 10;

    public static final Function<AppearanceDTO, AppearanceResource> APPEARANCE_MAPPER =
            appearance ->
                    AppearanceResource.builder()
                            .race(appearance.getRace())
                            .height(parseHeight(appearance.getHeight()))
                            .weight(parseWeight(appearance.getWeight()))
                            .eyes(appearance.getEyeColor())
                            .hair(appearance.getHairColor())
                            .build();
    public static final Function<PowerstatsDTO, PowerstatsResource> POWERSTATS_MAPPER =
            powerstats ->
                    PowerstatsResource.builder()
                        .combat(mapPowerStat(powerstats.getCombat()))
                        .intelligence(mapPowerStat(powerstats.getIntelligence()))
                        .durability(mapPowerStat(powerstats.getDurability()))
                        .power(mapPowerStat(powerstats.getPower()))
                        .strength(mapPowerStat(powerstats.getStrength()))
                        .speed(mapPowerStat(powerstats.getSpeed()))
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
                            .powerstats(POWERSTATS_MAPPER.apply(hero.getPowerstats()))
                            .groups(Set.of(hero.getConnections().getGroupAffiliation().split("; ")))
                            .build();

    private static int mapPowerStat(String value){
        try{
            return parseInt(value);
        } catch (NumberFormatException nfe){
            return DEFAULT_POWERSTAT_VALUE;
        }
    }
}
