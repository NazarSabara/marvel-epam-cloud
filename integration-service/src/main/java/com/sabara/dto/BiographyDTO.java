
package com.sabara.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BiographyDTO {

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
