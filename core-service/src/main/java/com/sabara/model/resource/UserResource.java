package com.sabara.model.resource;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserResource {

  private String username;
  private String email;
  private LocalDate birthDate;
  private String info;
}
