
package com.sabara.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchDTO {

    private String response;
    @JsonProperty("results")
    private List<HeroDTO> heroes;

}
