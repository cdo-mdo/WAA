package org.edu.miu.cs545de.backendapp.controller;

import org.edu.miu.cs545de.backendapp.model.CalculatorState;
import org.edu.miu.cs545de.backendapp.repository.CalculatorStateRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:3000")
public class CalculatorController {

    private final CalculatorStateRepository repo;

    public CalculatorController(CalculatorStateRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public CalculatorState get() {
        return repo.findById(1L).orElseGet(() -> repo.save(new CalculatorState(1L, 0.0)));
    }

    @PutMapping
    public CalculatorState set(@RequestBody CalculatorState body) {
        // force id=1
        CalculatorState state = new CalculatorState(1L, body.getValue());
        return repo.save(state);
    }
}
