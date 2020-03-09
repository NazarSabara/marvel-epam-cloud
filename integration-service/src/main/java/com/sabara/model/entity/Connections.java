package com.sabara.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Connections {

    @JsonProperty("group-affiliation")
    private String groupAffiliation;
    private String relatives;
}
