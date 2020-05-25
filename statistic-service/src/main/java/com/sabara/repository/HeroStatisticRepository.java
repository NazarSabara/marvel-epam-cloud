package com.sabara.repository;

import com.sabara.model.entity.HeroStats;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface HeroStatisticRepository extends ReactiveMongoRepository<HeroStats, String> {

    Mono<HeroStats> findByHero(String hero);
}
