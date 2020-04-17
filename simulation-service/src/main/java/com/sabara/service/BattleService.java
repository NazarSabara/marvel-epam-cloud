package com.sabara.service;

import com.sabara.dto.BattleDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.exception.UnprocessableEntityException;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleResults;
import com.sabara.model.resource.BattleType;
import com.sabara.utils.BattleUtils;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.sabara.utils.BattleUtils.*;
import static java.lang.String.format;
import static java.util.Collections.rotate;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.partition;

@Service
public class BattleService {

    public BattleResults battle(BattleDTO battle){
        validate(battle);

        BattleResults results = new BattleResults();
        results.setBattleType(battle.getBattleType());

        LinkedList<HeroDTO> currentTeam = new LinkedList<>();
        LinkedList<HeroDTO> opponentTeam = new LinkedList<>();
        splitHeroesIntoTeams(battle, currentTeam, opponentTeam);
        if(opponentTeam.contains(getFastestHero(battle.getHeroes()))){
            swapTeams(currentTeam, opponentTeam);
        }

        StopWatch executionTime = new StopWatch();

        currentTeam.sort(HERO_COMPARATOR);
        opponentTeam.sort(HERO_COMPARATOR);

        executionTime.start();
        do{
            performAttack(currentTeam.getFirst(), getRandomOpponent(opponentTeam), battle.getMap());
            opponentTeam.removeIf(BattleUtils::isDefeated);

            if(currentTeam.size() > 1) {
                rotate(currentTeam, -1);
            }
            swapTeams(currentTeam, opponentTeam);
        } while (isNull(getWinner(currentTeam, opponentTeam)));

        executionTime.stop();

        results.setWinner(results.getBattleType().getWinnerPrefix() + getWinner(currentTeam, opponentTeam));
        results.setSurvivors(requireNonNull(getWinner(currentTeam, opponentTeam))
                .stream()
                .map(hero -> format(WINNER_PATTERN, hero.getName(), hero.getPowerstats().getDurability()))
                .collect(toList()));
        results.setBattleDuration(calculateBattleDuration(executionTime.getTime()));
        results.setMap(battle.getMap());

        return results;
    }

    private void validate(BattleDTO battle){
        if(battle.getBattleType().equals(BattleType.TVT) && battle.getHeroes().size() != TVT_BATTLE_TEAM_SIZE * 2){
            throw new UnprocessableEntityException(TEAM_SIZE_ERROR);
        }

        if(battle.getBattleType().equals(BattleType.PVP) && battle.getHeroes().size() != 2){
            throw new UnprocessableEntityException(PLAYER_NOT_SELECTED_ERROR);
        }
    }

    private HeroDTO getFastestHero(List<HeroDTO> heroes){
        return heroes.stream().min(HERO_COMPARATOR).orElseThrow(() -> new UnprocessableEntityException(TEAM_SIZE_ERROR));
    }

    private HeroDTO getRandomOpponent(List<HeroDTO> opponentTeam){
        return opponentTeam.get(getRandomIndex(opponentTeam.size()));
    }

    private List<HeroDTO> getWinner(List<HeroDTO> firstTeam, List<HeroDTO> secondTeam){
        return firstTeam.isEmpty() ? secondTeam : secondTeam.isEmpty() ? firstTeam : null;
    }

    private void performAttack(HeroDTO currentHero, HeroDTO opponent, BattleMap map){
        opponent.getPowerstats().setDurability(opponent.getPowerstats().getDurability()
                - (receivedDmg(currentHero.getPowerstats(), currentHero.getAppearance().getRace(), opponent.getPowerstats(), map)));
    }

    private void splitHeroesIntoTeams(BattleDTO battle, List<HeroDTO> firstTeam, List<HeroDTO> secondTeam){
        int size = battle.getBattleType().equals(BattleType.PVP) ? 1 : TVT_BATTLE_TEAM_SIZE;

        firstTeam.addAll(partition(battle.getHeroes(), size).get(0));
        secondTeam.addAll(partition(battle.getHeroes(), size).get(1));
    }

    private void swapTeams(List<HeroDTO> currentTeam, List<HeroDTO> opponent){
        List<HeroDTO> tmp = new LinkedList<>(opponent);
        opponent.clear();
        opponent.addAll(currentTeam);
        currentTeam.clear();
        currentTeam.addAll(tmp);
    }
}
