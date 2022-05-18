package com.footballmanager.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String firstName;
    private String lastName;
    private Short age;
    private Short experienceMonths;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(id, player.id)
                && firstName.equals(player.firstName)
                && lastName.equals(player.lastName)
                && Objects.equals(age, player.age)
                && Objects.equals(experienceMonths, player.experienceMonths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, experienceMonths);
    }
}
