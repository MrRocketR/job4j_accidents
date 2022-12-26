package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accidents")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToMany
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany
    @JoinTable(name = "rules_accidents",
            joinColumns = {@JoinColumn(name = "fk_accident_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_rules_id")}
    )
    private Set<Rule> rules;
}