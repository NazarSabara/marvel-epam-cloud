package com.sabara.service;

import com.sabara.dto.BattleResultsDto;
import com.sabara.model.entity.HeroStats;
import com.sabara.repository.BattleStatisticRepository;
import com.sabara.repository.HeroStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Objects;

import static com.sabara.utils.StatsUtils.*;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticService {

  private final HeroStatisticRepository heroStatisticRepository;
  private final BattleStatisticRepository battleStatisticRepository;

  public Flux<HeroStats> get5MostPickedHeroes() {
    return heroStatisticRepository
        .findAll()
        .sort(Comparator.comparing(HeroStats::getPickCount))
        .take(5);
  }

  public void saveBattleResults(Mono<BattleResultsDto> battleResultsMono) {
    BattleResultsDto battleResults = battleResultsMono.block();

    if (Objects.nonNull(battleResults)) {
      battleResults.getWinners().stream()
          .forEach(
              winner -> {
                HeroStats currentStats =
                    heroStatisticRepository
                        .findByHero(winner)
                        .blockOptional()
                        .orElse(newHero(winner));
                heroStatisticRepository.save(updateHeroStats(currentStats, true));
              });

      battleResults.getLosers().stream()
          .forEach(
              loser -> {
                HeroStats currentStats =
                    heroStatisticRepository
                        .findByHero(loser)
                        .blockOptional()
                        .orElse(newHero(loser));
                heroStatisticRepository.save(updateHeroStats(currentStats, false));
              });

      battleStatisticRepository.insert(retrieveBattleStats(battleResults));
    }
  }
}
