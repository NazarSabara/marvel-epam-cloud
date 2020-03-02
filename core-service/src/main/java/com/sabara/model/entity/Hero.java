package com.sabara.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hero")
public class Hero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name", columnDefinition = "VARCHAR(25)", nullable = false, unique = true)
  private String name;
  @Column(name = "fullname", columnDefinition = "VARCHAR(25)")
  private String fullname;
  @Column(name = "place_of_birth", columnDefinition = "VARCHAR(25)")
  private String placeOfBirth;
  @Column(name = "work", columnDefinition = "VARCHAR(25)")
  private String work;
  @Column(name = "photo", columnDefinition = "VARCHAR(25)")
  private String photo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "appearance_id")
  private Appearance appearance;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "hero_group", joinColumns = @JoinColumn(name = "hero_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  @JsonIgnoreProperties("heroes")
  private Collection<Group> groups = new HashSet<>();
}
