package com.sabara.model.resource;

import lombok.Data;

import java.util.List;

@Data
public class BattleMap {

    private String name;
    private double gravitation;
    private double oxygenLevel;
    private List<String> inhabitants;
    private double naturalCondition;

}
