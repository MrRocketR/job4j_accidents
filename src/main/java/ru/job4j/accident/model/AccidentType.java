package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class AccidentType {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
}
