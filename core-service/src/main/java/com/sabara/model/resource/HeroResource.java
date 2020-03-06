package com.sabara.model.resource;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

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
