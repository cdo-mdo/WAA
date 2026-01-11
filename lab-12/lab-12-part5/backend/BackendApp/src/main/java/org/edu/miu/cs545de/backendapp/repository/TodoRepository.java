package org.edu.miu.cs545de.backendapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.edu.miu.cs545de.backendapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {}
