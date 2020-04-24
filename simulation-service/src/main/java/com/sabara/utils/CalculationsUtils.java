package com.sabara.utils;

import com.sabara.dto.PowerstatsDTO;
import com.sabara.model.resource.BattleMap;

import static com.sabara.utils.HeroUtils.isInhabitant;
import static com.sabara.utils.HeroUtils.strIsPrimaryAtt;
import static java.lang.Math.*;
import static org.apache.commons.lang.BooleanUtils.toInteger;

public class CalculationsUtils {

    public static final double PRIMARY_ATTRIBUTE_DMG_INCREASE = 1.5;
    public static final double INHABITANT_DMG_MULTIPLIER = 2;
    public static final double CRITICAL_DMG_MULTIPLIER = 1.5;
    public static final double CRITICAL_DMG_DIVIDER = 100;
    public static final double BLOCK_DIVIDER = 120;

    public static int calculatePureDmg(PowerstatsDTO powerstats, boolean isPhysicalDmg){
        var primaryAttributeDmg = isPhysicalDmg ? powerstats.getStrength() : powerstats.getIntelligence()
                * PRIMARY_ATTRIBUTE_DMG_INCREASE;
        var dmgPower = toInteger(strIsPrimaryAtt(powerstats))* pow(powerstats.getPower(), 2);

        return (int) sqrt(primaryAttributeDmg + dmgPower);
    }

    public static int calculateDmg(PowerstatsDTO powerstats, String race, BattleMap map){
        var isInhabitant = isInhabitant(race, map);
        var physicalPart = calculatePureDmg(powerstats, true) * map.getGravitation();
        var magicalPart = calculatePureDmg(powerstats, false) * map.getOxygenLevel();
        var naturalCondition =  toInteger(!isInhabitant) * map.getNaturalCondition();
        var inhabitantMultiplier = toInteger(isInhabitant) * INHABITANT_DMG_MULTIPLIER;

        return (int) ((inhabitantMultiplier + naturalCondition) * (physicalPart + magicalPart));
    }

    public static int calculatePureReduction(int primaryAtt, int combat, double mapInfluence){
        return (int) sqrt((sqrt((primaryAtt + combat) * mapInfluence)));
    }

    public static int calculateDmgReduction(PowerstatsDTO powerstats, BattleMap map){
        return strIsPrimaryAtt(powerstats)
                ? calculatePureReduction(powerstats.getStrength(), powerstats.getCombat(), map.getGravitation())
                : calculatePureReduction(powerstats.getIntelligence(), powerstats.getCombat(), map.getOxygenLevel());
    }

    public static int calculateReceivedDmg(PowerstatsDTO currentHero, String currentHeroRace,
                                           PowerstatsDTO opponent, BattleMap map){
        var comboDmg = calculateDmg(currentHero, currentHeroRace, map);
        var critMultiplier = isCrit(currentHero) ? CRITICAL_DMG_MULTIPLIER : 1;
        var dmgReduction = calculateDmgReduction(opponent, map);

        return (int) max(comboDmg * critMultiplier - dmgReduction, 0)
                * toInteger(isBlocked(opponent));
    }

    public static boolean isCrit(PowerstatsDTO powerstats){
        return random() <= powerstats.getPower() / CRITICAL_DMG_DIVIDER;
    }

    public static boolean isBlocked(PowerstatsDTO powerstats){
        return random() <= powerstats.getPower() / BLOCK_DIVIDER;
    }
}
