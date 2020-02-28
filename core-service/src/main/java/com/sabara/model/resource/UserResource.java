package com.sabara.model.resource;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResource {

  private String username;
  private String email;
  private Date birthDate;
  private String info;
}
