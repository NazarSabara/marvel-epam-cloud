package com.sabara.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppearanceDTO {

  private String race;
  private Double height;
  private Double weight;
  private String eyes;
  private String hair;

}
