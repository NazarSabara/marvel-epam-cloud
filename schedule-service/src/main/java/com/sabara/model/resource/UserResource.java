package com.sabara.model.resource;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResource {

  private String username;
  private String email;
  private LocalDate birthDate;
  private String info;
}
