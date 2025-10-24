package org.edu.miu.cs545de.springmvcapplication.repo;

import org.edu.miu.cs545de.springmvcapplication.domain.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProductRepository {
    private final Map<String, Product> store = new ConcurrentHashMap<>();

    public List<Product> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Product> findByNumber(String number) { return Optional.ofNullable(store.get(number)); }
    public void save(Product p) { store.put(p.getNumber(), p); }
    public void deleteByNumber(String number) { store.remove(number); }
}
