package com.sabara.utils;

public class BattleUtils {

    public static final int ROTATION_DISTANCE = -1;
    public static final String WINNER_PATTERN = "%s with %d hp";
    public static final String TEAM_SIZE_ERROR = "One of the teams is empty";
    public static final int DURATION_COEFFICIENT = 1000;

    public static long calculateBattleDuration(long executionTime){
        return executionTime / DURATION_COEFFICIENT;
    }
}
