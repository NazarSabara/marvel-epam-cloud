package com.sabara.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroDTO {

  private String name;
  private String placeOfBirth;
  private String work;
  private AppearanceDTO appearance;
  private Set<String> groups;
  private PowerstatsDTO powerstats;

}
