package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank; 
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A név nem lehet üres")
    private String name;

    @Min(value = 0, message = "Az életkor nem lehet negatív")
    private int age;

    private String gender;
}