package com.sabara.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "powerstats")
public class Powerstats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "combat", columnDefinition = "BIGINT")
    private Integer combat;
    @Column(name = "durability", columnDefinition = "BIGINT")
    private Integer durability;
    @Column(name = "intelligence", columnDefinition = "BIGINT")
    private Integer intelligence;
    @Column(name = "power", columnDefinition = "BIGINT")
    private Integer power;
    @Column(name = "speed", columnDefinition = "BIGINT")
    private Integer speed;
    @Column(name = "strength", columnDefinition = "BIGINT")
    private Integer strength;
}
