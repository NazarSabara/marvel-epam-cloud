package com.sabara.model.resource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BattleType {
    PVP("Player"),
    TVT("Team");

    @Getter
    private final String winnerPrefix;

}
