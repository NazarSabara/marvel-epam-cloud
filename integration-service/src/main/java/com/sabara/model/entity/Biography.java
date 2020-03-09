package com.sabara.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Biography {

    private List<String> aliases;
    private String alignment;
    @JsonProperty("alter-egos")
    private String alterEgos;
    @JsonProperty("first-appearance")
    private String firstAppearance;
    @JsonProperty("full-name")
    private String fullName;
    @JsonProperty("place-of-birth")
    private String placeOfBirth;
    private String publisher;
}
