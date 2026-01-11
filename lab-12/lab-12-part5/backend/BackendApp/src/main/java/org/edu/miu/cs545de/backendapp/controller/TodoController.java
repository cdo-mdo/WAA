package org.edu.miu.cs545de.backendapp.controller;

import org.edu.miu.cs545de.backendapp.model.Todo;
import org.edu.miu.cs545de.backendapp.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    private final TodoRepository repo;

    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Todo> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Todo add(@RequestBody Todo todo) {
        todo.setId(null);
        return repo.save(todo);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

