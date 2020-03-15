package com.sabara.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class HeroDTO {

  private String name;
  private String photo;
  private AppearanceDTO appearance;

}
