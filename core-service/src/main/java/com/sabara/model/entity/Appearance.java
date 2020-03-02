package com.sabara.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appearance")
public class Appearance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String race;
  private double height;
  private double weight;
  private String eyes;
  private String hair;

}
