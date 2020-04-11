package com.sabara.utils;

import com.sabara.model.resource.BattleMap;

import java.util.Random;

import static java.lang.Math.*;

public class BattleUtils {

    private static final Random random = new Random();

    public static double physDmg(int strength, int power, boolean strIsPrimaryAtt){
        return sqrt(strength * 1.5 + (strIsPrimaryAtt ? 1 : 0) * pow(power, 2));
    }

    public static double magDmg(int intelligence, int power, boolean strIsPrimaryAtt){
        return sqrt(intelligence * 1.5 + (!strIsPrimaryAtt ? 1 : 0) * pow(power, 2));
    }

    public static double comboDmg(double physDmg, double magDmg, boolean isInhabitant, BattleMap map){
        return ((isInhabitant ? 1 : 0) * 2 + (!isInhabitant ? 1 : 0) * map.getNaturalCondition())
                * (physDmg * map.getGravitation() + magDmg * map.getOxygenLevel());
    }

    public static double dmgReduction(int strength, int intelligence, int combat, boolean strIsPrimaryAtt, BattleMap map){
        return sqrt((sqrt((strIsPrimaryAtt ? strength : intelligence) + combat)
                * (strIsPrimaryAtt ? map.getGravitation() : map.getOxygenLevel())));
    }

    public static double receivedDmg(double comboDmg, double dmgReduction, boolean isCrit, boolean isBlocked){
        return max(comboDmg * (isCrit ? 1.5 : 1) - dmgReduction, 0) * (isBlocked ? 0 : 1);
    }

    public static boolean isCrit(int power){
        return random() <= power / 100;
    }

    public static boolean isBlocked(int power){
        return random() <= power / 120;
    }

    public static int getRandomIndex(int bound){
        return random.nextInt(bound);
    }
}
