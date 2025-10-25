package cs545de.springmvcapplication.service;

import cs545de.springmvcapplication.model.CartItem;
import cs545de.springmvcapplication.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    // key = productNumber
    private final Map<String, CartItem> cart = new LinkedHashMap<>();

    public List<CartItem> items() {
        return new ArrayList<>(cart.values());
    }

    public int totalQuantity() {
        return cart.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public void add(Product p) {
        cart.compute(p.getProductNumber(), (k, v) -> {
            if (v == null) return new CartItem(p, 1);
            v.setQuantity(v.getQuantity() + 1);
            return v;
        });
    }

    public void removeOne(String productNumber) {
        CartItem item = cart.get(productNumber);
        if (item == null) return;
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
        } else {
            cart.remove(productNumber);
        }
    }

    public void removeAll(String productNumber) {
        cart.remove(productNumber);
    }

    public void clear() {
        cart.clear();
    }
}
