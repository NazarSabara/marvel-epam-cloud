package com.sabara.model.resource;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class HeroResource {

  private String name;
  private String fullname;
  private String placeOfBirth;
  private String work;
  private String photo;
  private AppearanceResource appearance;
  private Set<String> groups;

}
