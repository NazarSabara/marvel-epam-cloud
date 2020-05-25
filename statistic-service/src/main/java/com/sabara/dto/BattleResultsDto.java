package com.sabara.dto;

import lombok.Data;

import java.util.List;

@Data
public class BattleResultsDto {

    private String battleType;
    private String winnerTag;
    private List<String> survivors;
    private List<String> winners;
    private List<String> losers;
    private long battleDuration;

}
