package com.sabara.model.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppearanceResource {

  private String race;
  private Double height;
  private Double weight;
  private String eyes;
  private String hair;
}
