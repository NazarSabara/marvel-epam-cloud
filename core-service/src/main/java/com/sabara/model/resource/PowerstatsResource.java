package com.sabara.model.resource;

import lombok.Builder;
import lombok.Data;

@Data
public class PowerstatsResource {

    private Integer combat;
    private Integer durability;
    private Integer intelligence;
    private Integer power;
    private Integer speed;
    private Integer strength;
}
