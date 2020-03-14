
package com.sabara.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDTO {

    private String response;
    @JsonProperty("results")
    private List<HeroDTO> heroes;

}
