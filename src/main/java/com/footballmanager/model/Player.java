package com.footballmanager.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 250, message = "First name must be between 3 and 250 characters")
    private String firstName;
    @NotNull
    @Size(min = 3, max = 250, message = "Last name must be between 3 and 250 characters")
    private String lastName;
    @Positive
    private Short age;
    @PositiveOrZero
    private Short experienceMonths;
}
