package com.sabara.model.resource;

public enum BattleType {
    PVP("Player"),
    TVT("Team");

    private final String winnerPrefix;

    BattleType(String winnerPrefix){
        this.winnerPrefix = winnerPrefix;
    }

    public String getWinnerPrefix(){
        return winnerPrefix;
    }

}
