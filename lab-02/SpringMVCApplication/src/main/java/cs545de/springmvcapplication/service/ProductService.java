package cs545de.springmvcapplication.service;

import cs545de.springmvcapplication.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    // key = productNumber
    private final Map<String, Product> store = new ConcurrentHashMap<>();

    public List<Product> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Product::getProductNumber))
                .toList();
    }

    public Optional<Product> findByNumber(String number) {
        return Optional.ofNullable(store.get(number));
    }

    public boolean exists(String number) { return store.containsKey(number); }

    public void save(Product p) { store.put(p.getProductNumber(), p); }

    public void delete(String number) { store.remove(number); }

    // seed data (optional)
    public ProductService() {
        save(new Product("P1", "Pencil", 1.00));
        save(new Product("P2", "Paper", 2.00));
    }
}

