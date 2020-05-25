package com.sabara.model.resource;

import lombok.Data;
import java.util.Set;

@Data
public class HeroResource {

  private String name;
  private String fullname;
  private String placeOfBirth;
  private String work;
  private String photo;
  private AppearanceResource appearance;
  private PowerstatsResource powerstats;
  private Set<String> groups;

}
