package com.sabara.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Appearance {

    @JsonProperty("eye-color")
    private String eyeColor;
    private String gender;
    @JsonProperty("hair-color")
    private String hairColor;
    private List<String> height;
    private String race;
    private List<String> weight;
}
