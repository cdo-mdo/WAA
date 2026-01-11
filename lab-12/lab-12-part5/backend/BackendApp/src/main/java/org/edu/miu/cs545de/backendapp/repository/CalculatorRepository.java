package org.edu.miu.cs545de.backendapp.repository;

import org.edu.miu.cs545de.backendapp.model.CalculatorState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorStateRepository extends JpaRepository<CalculatorState, Long> {}
