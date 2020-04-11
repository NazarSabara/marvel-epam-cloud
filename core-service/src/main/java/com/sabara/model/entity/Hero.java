package com.sabara.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "hero")
public class Hero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", columnDefinition = "VARCHAR(350)", nullable = false, unique = true)
  private String name;
  @Column(name = "fullname", columnDefinition = "VARCHAR(350)")
  private String fullname;
  @Column(name = "place_of_birth", columnDefinition = "VARCHAR(350)")
  private String placeOfBirth;
  @Column(name = "work", columnDefinition = "VARCHAR(350)")
  private String work;
  @Column(name = "photo", columnDefinition = "VARCHAR(350)")
  private String photo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "appearance_id")
  private Appearance appearance;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Group.class)
  @JoinTable(name = "hero_group", joinColumns = @JoinColumn(name = "hero_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  @JsonIgnoreProperties("heroes")
  private Collection<Group> groups = new HashSet<>();
}
