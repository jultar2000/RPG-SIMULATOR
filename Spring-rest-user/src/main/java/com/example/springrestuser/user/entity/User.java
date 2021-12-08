package com.example.springrestuser.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "password")
    @ToString.Exclude
    private String password;

    @Column(name = "email", unique = true)
    private String email;
}
