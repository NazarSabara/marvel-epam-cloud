package com.sabara.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero {

    private String id;
    private Appearance appearance;
    private Biography biography;
    private Connections connections;
    private Map<String, String> image;
    private String name;
    private Powerstats powerstats;
    private Work work;
}
