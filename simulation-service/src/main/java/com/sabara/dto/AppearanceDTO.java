package com.sabara.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppearanceDTO {

  private String race;
  private Double height;
  private Double weight;
  private String eyes;
  private String hair;
}
