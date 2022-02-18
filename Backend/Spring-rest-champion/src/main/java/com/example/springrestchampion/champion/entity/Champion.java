package com.example.springrestchampion.champion.entity;

import com.example.springrestchampion.user.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "champions")
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "backstory")
    private String backstory;

    @Column(name = "experience")
    private Long experience;

    @Column(name = "manaPoints")
    private Long manaPoints;

    @Column(name = "healthPoints")
    private Long healthPoints;

    @Column(name = "inteligence")
    private int inteligence;

    @Column(name = "strength")
    private int strength;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "race")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "championUser")
    private User championUser;
}
