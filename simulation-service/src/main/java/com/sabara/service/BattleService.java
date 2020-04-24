package com.sabara.service;

import com.sabara.dto.BattleDTO;
import com.sabara.dto.HeroDTO;
import com.sabara.exception.UnprocessableEntityException;
import com.sabara.model.resource.BattleMap;
import com.sabara.model.resource.BattleResults;
import javafx.util.Pair;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static com.sabara.utils.BattleUtils.*;
import static com.sabara.utils.CalculationsUtils.calculateReceivedDmg;
import static com.sabara.utils.HeroUtils.HERO_COMPARATOR;
import static com.sabara.utils.HeroUtils.isDefeated;
import static java.lang.String.format;
import static java.util.Collections.rotate;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.BooleanUtils.toInteger;

@Service
public class BattleService {

    public BattleResults battle(BattleDTO battle){
        validate(battle);

        BattleResults results = new BattleResults();
        results.setBattleType(battle.getBattleType());

        LinkedList<Pair<Boolean, HeroDTO>> heroes = Stream.of(battle.getFirstTeam(), battle.getSecondTeam())
            .flatMap(Collection::stream)
            .sorted(HERO_COMPARATOR)
            .map(hero -> new Pair<>(battle.getFirstTeam().contains(hero), hero))
            .collect(toCollection(LinkedList::new));

        StopWatch executionTime = new StopWatch();
        executionTime.start();

        Stream.generate(heroes::getFirst)
            .takeWhile(hero ->
                hero.getKey() ? teamIsDefeated(battle.getSecondTeam()) : teamIsDefeated(battle.getFirstTeam()))
            .forEach(hero -> {
                performAttack(hero.getValue(), getRandomOpponent(heroes), battle.getMap());
                heroes.removeIf(opponent -> isDefeated(opponent.getValue()));

                rotate(heroes, ROTATION_DISTANCE);
            });

        executionTime.stop();

        results.setWinner(results.getBattleType().getWinnerPrefix() + getWinnerIndex(heroes.getFirst().getKey()));
        results.setSurvivors(requireNonNull(heroes)
                .stream()
                .map(hero -> format(WINNER_PATTERN, hero.getValue().getName(), hero.getValue().getPowerstats().getDurability()))
                .collect(toList()));
        results.setBattleDuration(calculateBattleDuration(executionTime.getTime()));
        results.setMap(battle.getMap());

        return results;
    }

    private void validate(BattleDTO battle){
        if(battle.getFirstTeam().isEmpty() || battle.getSecondTeam().isEmpty()){
            throw new UnprocessableEntityException(TEAM_SIZE_ERROR);
        }
    }

    private HeroDTO getRandomOpponent(LinkedList<Pair<Boolean, HeroDTO>> heroes){
        boolean currentTeam = heroes.getFirst().getKey();
        return heroes.stream()
                .skip(1)
                .filter(value -> value.getKey() != currentTeam)
                .findAny()
                .map(Pair::getValue)
                .orElse(null);
    }

    private int getWinnerIndex(boolean currentTeam){
        return toInteger(currentTeam) + 1;
    }

    private boolean teamIsDefeated(List<HeroDTO> team){
        return team.stream().anyMatch(hero -> hero.getPowerstats().getDurability() > 0);
    }

    private void performAttack(HeroDTO currentHero, HeroDTO opponent, BattleMap map){
        if(Objects.isNull(opponent)){
            return;
        }

        var opponentPowerStats = opponent.getPowerstats();
        var receivedDmg = calculateReceivedDmg(currentHero.getPowerstats(), currentHero.getAppearance().getRace(), opponentPowerStats, map);
        opponentPowerStats.setDurability(opponentPowerStats.getDurability() - receivedDmg);
    }
}
