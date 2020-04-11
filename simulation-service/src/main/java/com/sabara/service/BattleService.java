package com.sabara.service;

import com.sabara.dto.HeroDTO;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleResults;
import com.sabara.model.resource.BattleType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sabara.utils.BattleUtils.*;
import static java.lang.String.format;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

@Service
public class BattleService {

    public BattleResults battle(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam, BattleMap map){
        BattleResults results = new BattleResults();
        results.setBattleType(firstTeam.size() == 1 ? BattleType.PVP : BattleType.TVT);

        int currentTurn = 1;

        firstTeam.sort(comparingInt(h -> h.getPowerstats().getSpeed()));
        secondTeam.sort(comparingInt(h -> h.getPowerstats().getSpeed()));
        int firstTeamIndex = 0;
        int secondTeamIndex = 0;
        boolean currentFirstTeam = firstTeam.get(firstTeamIndex).getPowerstats().getSpeed() > secondTeam.get(secondTeamIndex).getPowerstats().getSpeed();

        HeroDTO currentHero;
        HeroDTO currentOpponent;

        while (true){
            currentHero = currentFirstTeam ? firstTeam.get(firstTeamIndex++) : secondTeam.get(secondTeamIndex++);
            currentOpponent = getRandomOpponent(firstTeam, secondTeam, currentFirstTeam);

            boolean currentIsStrBasedHero =
                    currentHero.getPowerstats().getStrength() > currentHero.getPowerstats().getIntelligence();
            boolean currentIsInhabitant =map.getInhabitants().contains(currentHero.getAppearance().getRace());
            boolean opponentIsStrBasedHero =
                    currentHero.getPowerstats().getStrength() > currentHero.getPowerstats().getIntelligence();

            double pDmg = physDmg(currentHero.getPowerstats().getStrength(),
                            currentHero.getPowerstats().getPower(),
                            currentIsStrBasedHero);
            double mDmg = magDmg(currentHero.getPowerstats().getIntelligence(),
                            currentHero.getPowerstats().getPower(),
                            currentIsStrBasedHero);
            double comboDmg = comboDmg(pDmg, mDmg, currentIsInhabitant, map);
            double dmgReduction = dmgReduction(currentOpponent.getPowerstats().getStrength(),
                    currentOpponent.getPowerstats().getIntelligence(),
                    currentOpponent.getPowerstats().getCombat(),
                    opponentIsStrBasedHero, map);

            currentOpponent.getPowerstats().setDurability(currentOpponent.getPowerstats().getDurability()
                    - (int)(receivedDmg(comboDmg, dmgReduction, isCrit(currentHero.getPowerstats().getPower()),
                    isBlocked(currentOpponent.getPowerstats().getPower()))));

            if(currentOpponent.getPowerstats().getDurability() <= 0){
                getOpponentTeam(firstTeam, secondTeam, currentFirstTeam).remove(currentOpponent);

                if(getOpponentTeam(firstTeam, secondTeam, currentFirstTeam).isEmpty()){
                    results.setWinner(results.getBattleType().getWinnerPrefix() + (currentFirstTeam ? " 1" : " 2"));
                    results.setSurvivors(getOpponentTeam(firstTeam, secondTeam, !currentFirstTeam)
                            .stream()
                            .map(hero -> format("%s with %d hp", hero.getName(), hero.getPowerstats().getDurability()))
                            .collect(toList()));
                    results.setBattleDuration(currentTurn);
                    results.setMap(map);

                    return results;
                }
            }

            currentFirstTeam = !currentFirstTeam;
            firstTeamIndex = firstTeamIndex % firstTeam.size();
            secondTeamIndex = secondTeamIndex % secondTeam.size();
            currentTurn++;
        }
    }

    private HeroDTO getRandomOpponent(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam, boolean currentFirstTeam){
        return currentFirstTeam ? secondTeam.get(getRandomIndex(secondTeam.size()))
                : firstTeam.get(getRandomIndex(firstTeam.size()));
    }

    private List<HeroDTO> getOpponentTeam(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam, boolean currentFirstTeam){
        return currentFirstTeam ? secondTeam : firstTeam;
    }
}
