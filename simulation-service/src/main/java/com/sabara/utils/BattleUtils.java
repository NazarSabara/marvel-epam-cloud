package com.sabara.utils;

import com.sabara.dto.BattleDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.dto.PowerstatsDTO;
import com.sabara.exception.UnprocessableEntityException;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleType;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;
import static java.util.Comparator.comparingInt;
import static org.apache.commons.collections4.ListUtils.partition;
import static org.apache.commons.lang.BooleanUtils.toInteger;

public class BattleUtils {

    public static final String WINNER_PATTERN = "%s with %d hp";
    public static final String TEAM_SIZE_ERROR = "One of the team is not full";
    public static final String PLAYER_NOT_SELECTED_ERROR = "One of the heroes is not selected";
    public static final double PRIMARY_ATTRIBUTE_DMG_INCREASE = 1.5;
    public static final double INHABITANT_DMG_MULTIPLIER = 2;
    public static final double CRITICAL_DMG_MULTIPLIER = 1.5;
    public static final double CRITICAL_DMG_DIVIDER = 100;
    public static final double BLOCK_DIVIDER = 120;
    public static final int DURATION_COEFFICIENT = 1000;
    public static final int TVT_BATTLE_TEAM_SIZE = 5;

    public static Comparator<HeroDTO> HERO_COMPARATOR = comparingInt(h -> h.getPowerstats().getSpeed());

    private static final Random random = new Random();

    public static int pureDmg(PowerstatsDTO powerstats, boolean isPhysicalDmg){
        return (int) sqrt((isPhysicalDmg ? powerstats.getStrength() : powerstats.getIntelligence())
                * PRIMARY_ATTRIBUTE_DMG_INCREASE + toInteger(strIsPrimaryAtt(powerstats)) * pow(powerstats.getPower(), 2));
    }

    public static int comboDmg(PowerstatsDTO powerstats, String race, BattleMap map){
        return (int) ((toInteger(isInhabitant(race, map)) * INHABITANT_DMG_MULTIPLIER
                + toInteger(!isInhabitant(race, map)) * map.getNaturalCondition())
                * (pureDmg(powerstats, true) * map.getGravitation()
                + pureDmg(powerstats, false) * map.getOxygenLevel()));
    }

    public static int pureReduction(int primaryAtt, int combat, double mapInfluence){
        return (int) sqrt((sqrt((primaryAtt + combat) * mapInfluence)));
    }

    public static int dmgReduction(PowerstatsDTO powerstats, BattleMap map){
        return strIsPrimaryAtt(powerstats)
                ? pureReduction(powerstats.getStrength(), powerstats.getCombat(), map.getGravitation())
                : pureReduction(powerstats.getIntelligence(), powerstats.getCombat(), map.getOxygenLevel());
    }

    public static int receivedDmg(PowerstatsDTO currentHero, String currentHeroRace,
                                     PowerstatsDTO opponent, BattleMap map){
        return (int)max(comboDmg(currentHero, currentHeroRace, map) * (isCrit(currentHero) ? CRITICAL_DMG_MULTIPLIER : 1)
                - dmgReduction(opponent, map), 0) * toInteger(isBlocked(opponent));
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

    public static long calculateBattleDuration(long executionTime){
        return executionTime / DURATION_COEFFICIENT;
    }

    public static boolean isDefeated(HeroDTO opponent){
        return opponent.getPowerstats().getDurability() <= 0;
    }
}
