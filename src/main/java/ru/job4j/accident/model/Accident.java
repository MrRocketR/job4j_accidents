package ru.job4j.accident.model;

import lombok.*;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {

    @EqualsAndHashCode.Include
    private AtomicInteger id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;

}
