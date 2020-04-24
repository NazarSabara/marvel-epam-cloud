package com.sabara.utils;

import com.sabara.dto.HeroDTO;
import com.sabara.dto.PowerstatsDTO;
import com.sabara.model.resource.BattleMap;

import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class HeroUtils {

    public static Comparator<HeroDTO> HERO_COMPARATOR = comparingInt(h -> h.getPowerstats().getSpeed());

    public static boolean strIsPrimaryAtt(PowerstatsDTO powerstats){
        return powerstats.getStrength() > powerstats.getIntelligence();
    }

    public static boolean isDefeated(HeroDTO opponent){
        return opponent.getPowerstats().getDurability() <= 0;
    }

    public static boolean isInhabitant(String race, BattleMap map){
        return map.getInhabitants().contains(race);
    }

}
