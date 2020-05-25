package com.sabara.repository;

import com.sabara.model.entity.BattleStats;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BattleStatisticRepository extends ReactiveMongoRepository<BattleStats, String> {
}
