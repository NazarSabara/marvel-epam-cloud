package com.sabara.model.entity;

import javax.persistence.Column;
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
  @Column(name = "race", columnDefinition = "VARCHAR(25)")
  private String race;
  @Column(name = "height", columnDefinition = "DOUBLE")
  private double height;
  @Column(name = "weight", columnDefinition = "DOUBLE")
  private double weight;
  @Column(name = "eyes", columnDefinition = "VARCHAR(25)")
  private String eyes;
  @Column(name = "hair", columnDefinition = "VARCHAR(25)")
  private String hair;

}
