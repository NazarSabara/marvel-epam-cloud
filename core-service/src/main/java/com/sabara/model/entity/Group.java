package com.sabara.model.entity;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "alliance")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name", columnDefinition = "VARCHAR(25)", nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "groups")
  private Collection<Hero> heroes = new HashSet<>();
}
