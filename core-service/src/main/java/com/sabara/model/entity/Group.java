package com.sabara.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "alliance")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", columnDefinition = "VARCHAR(350)", nullable = false)
  private String name;

  @ManyToMany(mappedBy = "groups")
  private Set<Hero> heroes = new HashSet<>();
}
