
package com.sabara.dto;

import lombok.Data;

@Data
public class HeroDTO {

    private AppearanceDTO appearance;
    private BiographyDTO biography;
    private ConnectionsDTO connections;
    private String id;
    private ImageDTO image;
    private String name;
    private PowerstatsDTO powerstats;
    private WorkDTO work;

}
