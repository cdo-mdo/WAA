package org.edu.miu.cs545de.springmvcapplication.service;

import org.edu.miu.cs545de.springmvcapplication.domain.Product;
import org.edu.miu.cs545de.springmvcapplication.repo.InMemoryProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final InMemoryProductRepository repo = new InMemoryProductRepository();

    public List<Product> all() { return repo.findAll(); }
    public Optional<Product> get(String number) { return repo.findByNumber(number); }
    public void add(Product p) { repo.save(p); }
    public void remove(String number) { repo.deleteByNumber(number); }
}

