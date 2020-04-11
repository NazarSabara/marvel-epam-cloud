
package com.sabara.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AppearanceDTO {

    @JsonProperty("eye-color")
    private String eyeColor;
    private String gender;
    @JsonProperty("hair-color")
    private String hairColor;
    private List<String> height;
    private String race;
    private List<String> weight;

}
