package com.sabara.service;

import com.sabara.model.resource.BattleResults;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticServiceClient {

    private final WebClient statisticServiceClient;

    public void saveBattleResults(Mono<BattleResults> battleResults){
        statisticServiceClient.post().uri("/stats").body(battleResults, BattleResults.class).exchange();
    }
}
