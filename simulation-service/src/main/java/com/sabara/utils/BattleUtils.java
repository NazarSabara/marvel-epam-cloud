package com.sabara.utils;

import com.sabara.dto.PowerstatsDTO;
import com.sabara.model.resource.BattleMap;

import java.util.Random;

import static java.lang.Math.*;

public class BattleUtils {

    public static final String WINNER_PATTERN = "%s with %d hp";
    public static final double PRIMARY_ATTRIBUTE_DMG_INCREASE = 1.5;
    public static final double INHABITANT_DMG_MULTIPLIER = 2;
    public static final double CRITICAL_DMG_MULTIPLIER = 1.5;
    public static final double CRITICAL_DMG_DIVIDER = 100;
    public static final double BLOCK_DIVIDER = 120;

    private static final Random random = new Random();

    public static int pureDmg(PowerstatsDTO powerstats, boolean isPhysicalDmg){
        return (int) sqrt((isPhysicalDmg ? powerstats.getStrength() : powerstats.getIntelligence())
                * PRIMARY_ATTRIBUTE_DMG_INCREASE + (strIsPrimaryAtt(powerstats) ? 1 : 0) * pow(powerstats.getPower(), 2));
    }

    public static int comboDmg(PowerstatsDTO powerstats, String race, BattleMap map){
        return (int) (((isInhabitant(race, map) ? 1 : 0) * INHABITANT_DMG_MULTIPLIER
                + (!isInhabitant(race, map) ? 1 : 0) * map.getNaturalCondition())
                * (pureDmg(powerstats, true) * map.getGravitation()
                + pureDmg(powerstats, false) * map.getOxygenLevel()));
    }

    public static int dmgReduction(PowerstatsDTO powerstats, BattleMap map){
        return (int) sqrt((sqrt((strIsPrimaryAtt(powerstats) ? powerstats.getStrength() : powerstats.getIntelligence())
                + powerstats.getCombat()) * (strIsPrimaryAtt(powerstats) ? map.getGravitation() : map.getOxygenLevel())));
    }

    public static int receivedDmg(PowerstatsDTO currentHero, String currentHeroRace,
                                     PowerstatsDTO opponent, BattleMap map){
        return (int)max(comboDmg(currentHero, currentHeroRace, map) * (isCrit(currentHero) ? CRITICAL_DMG_MULTIPLIER : 1)
                - dmgReduction(opponent, map), 0) * (isBlocked(opponent) ? 0 : 1);
    }

    public static boolean strIsPrimaryAtt(PowerstatsDTO powerstats){
        return powerstats.getStrength() > powerstats.getIntelligence();
    }

    public static boolean isInhabitant(String race, BattleMap map){
        return map.getInhabitants().contains(race);
    }

    public static boolean isCrit(PowerstatsDTO powerstats){
        return random() <= powerstats.getPower() / CRITICAL_DMG_DIVIDER;
    }

    public static boolean isBlocked(PowerstatsDTO powerstats){
        return random() <= powerstats.getPower() / BLOCK_DIVIDER;
    }

    public static int getRandomIndex(int bound){
        return random.nextInt(bound);
    }
}
