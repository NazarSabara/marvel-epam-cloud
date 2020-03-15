
package com.sabara.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConnectionsDTO {

    @JsonProperty("group-affiliation")
    private String groupAffiliation;

}
