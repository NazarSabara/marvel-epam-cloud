package com.sabara.utils;

import com.sabara.dto.BattleResultsDto;
import com.sabara.model.entity.BattleStats;
import com.sabara.model.entity.HeroStats;

import java.util.UUID;

public class StatsUtils {

    public static HeroStats newHero(String hero){
        return new HeroStats()
            .setId(UUID.randomUUID().toString())
            .setHero(hero)
            .setPickCount(0L)
            .setWinCount(0L)
            .setLoseCount(0L);
    }

    public static HeroStats updateHeroStats(HeroStats currentStats, boolean isWinner){
        currentStats.setPickCount(currentStats.getPickCount() + 1);
        if(isWinner){
            currentStats.setWinCount(currentStats.getWinCount() + 1);
        } else {
            currentStats.setLoseCount(currentStats.getLoseCount() + 1);
        }

        return currentStats;
    }

    public static BattleStats retrieveBattleStats(BattleResultsDto battleResults){
        return new BattleStats()
            .setId(UUID.randomUUID().toString())
            .setBattleDuration(battleResults.getBattleDuration());
    }
}
