package com.sabara.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "username", columnDefinition = "VARCHAR(25)", nullable = false)
  private String username;
  @Column(name = "password", columnDefinition = "VARCHAR(25)", nullable = false)
  private String password;
  @Column(name = "email", columnDefinition = "VARCHAR(25)", nullable = false)
  private String email;
  @Column(name = "birth_date", columnDefinition = "DATE")
  private LocalDate birthDate;
  @Column(name = "info", columnDefinition = "VARCHAR(25)")
  private String info;
}
