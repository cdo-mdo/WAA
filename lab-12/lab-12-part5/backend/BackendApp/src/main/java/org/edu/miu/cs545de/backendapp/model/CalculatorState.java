package org.edu.miu.cs545de.backendapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CalculatorState {
    @Id
    private Long id; // always 1

    private double value;
}
