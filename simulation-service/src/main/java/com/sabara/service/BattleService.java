package com.sabara.service;

import com.sabara.dto.HeroDTO;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleResults;
import com.sabara.model.resource.BattleType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.sabara.utils.BattleUtils.*;
import static java.lang.String.format;
import static java.util.Comparator.comparingInt;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static java.util.Objects.isNull;

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

        HeroDTO currentHero = getFirstHero(firstTeam, secondTeam);

        do{
            performAttack(firstTeam, secondTeam, currentHero, map);

            firstTeamIndex = firstTeamIndex % firstTeam.size();
            secondTeamIndex = secondTeamIndex % secondTeam.size();
            currentTurn++;

            currentHero = firstTeam.contains(currentHero) ? secondTeam.get(secondTeamIndex++) : firstTeam.get(firstTeamIndex++);
        } while (isNull(getWinner(firstTeam, secondTeam)));

        results.setWinner(results.getBattleType().getWinnerPrefix() + getWinner(firstTeam, secondTeam));
        results.setSurvivors(requireNonNull(getWinner(firstTeam, secondTeam))
                .stream()
                .map(hero -> format(WINNER_PATTERN, hero.getName(), hero.getPowerstats().getDurability()))
                .collect(toList()));
        results.setBattleDuration(currentTurn);
        results.setMap(map);

        return results;
    }

    private HeroDTO getRandomOpponentForHero(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam, HeroDTO currentHero){
        return firstTeam.contains(currentHero) ? secondTeam.get(getRandomIndex(secondTeam.size()))
                : firstTeam.get(getRandomIndex(firstTeam.size()));
    }

    // heroIsOpponent means that hero passed to this function counted as opponent
    private List<HeroDTO> getTeamByHero(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam, HeroDTO hero){
        return firstTeam.contains(hero) ? secondTeam : firstTeam;
    }

    private List<HeroDTO> getWinner(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam){
        return firstTeam.isEmpty() ? secondTeam : secondTeam.isEmpty() ? firstTeam : null;
    }

    private HeroDTO getFirstHero(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam){
        return firstTeam.get(0).getPowerstats().getSpeed() > secondTeam.get(0).getPowerstats().getSpeed()
                ? firstTeam.get(0) : secondTeam.get(0);
    }

    private void performAttack(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam, HeroDTO currentHero, BattleMap map){
        HeroDTO opponent = getRandomOpponentForHero(firstTeam, secondTeam, currentHero);

        opponent.getPowerstats().setDurability(opponent.getPowerstats().getDurability()
                - (receivedDmg(currentHero.getPowerstats(), currentHero.getAppearance().getRace(), opponent.getPowerstats(), map)));

        if(opponent.getPowerstats().getDurability() <= 0){
            getTeamByHero(firstTeam, secondTeam, opponent).remove(opponent);
        }
    }
}
