package com.sabara.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "alliance")
@RequiredArgsConstructor
@NoArgsConstructor
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NonNull
  @Column(name = "name", columnDefinition = "VARCHAR(350)", nullable = false)
  private String name;

  @ManyToMany(mappedBy = "groups")
  private Collection<Hero> heroes = new HashSet<>();
}
