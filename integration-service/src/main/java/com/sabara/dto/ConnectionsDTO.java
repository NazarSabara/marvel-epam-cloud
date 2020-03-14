
package com.sabara.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionsDTO {

    @JsonProperty("group-affiliation")
    private String groupAffiliation;

}
