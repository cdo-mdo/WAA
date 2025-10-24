package org.edu.miu.cs545de.springmvcapplication.web;

import jakarta.validation.Valid;
import org.edu.miu.cs545de.springmvcapplication.domain.CartItem;
import org.edu.miu.cs545de.springmvcapplication.domain.Product;
import org.edu.miu.cs545de.springmvcapplication.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/products")
@SessionAttributes("cart")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Initialize session cart
    @ModelAttribute("cart")
    public Map<String, CartItem> cart() {
        return new LinkedHashMap<>();
    }

    @GetMapping
    public String list(Model model, @ModelAttribute("cart") Map<String, CartItem> cart) {
        model.addAttribute("products", service.all());
        model.addAttribute("cartItems", cart.values());
        return "products/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute("product") Product product,
                      BindingResult result,
                      RedirectAttributes ra) {
        if (result.hasErrors()) {
            // return to form with validation messages (no redirect)
            return "products/form";
        }
        // Save then redirect (PRG)
        service.add(product);
        ra.addFlashAttribute("msg", "Product added: " + product.getNumber());
        return "redirect:/products";
    }

    @PostMapping("/{number}/delete")
    public String delete(@PathVariable String number, RedirectAttributes ra) {
        service.remove(number);
        ra.addFlashAttribute("msg", "Product removed: " + number);
        return "redirect:/products";
    }

    @PostMapping("/{number}/cart/add")
    public String addToCart(@PathVariable String number,
                            @ModelAttribute("cart") Map<String, CartItem> cart,
                            RedirectAttributes ra) {
        service.get(number).ifPresent(p -> {
            cart.compute(number, (k, v) -> {
                if (v == null) return new CartItem(p);
                v.increment();
                return v;
            });
        });
        ra.addFlashAttribute("msg", "Added to cart: " + number);
        return "redirect:/products";
    }

    @PostMapping("/{number}/cart/remove")
    public String removeFromCart(@PathVariable String number,
                                 @ModelAttribute("cart") Map<String, CartItem> cart,
                                 RedirectAttributes ra) {
        CartItem item = cart.get(number);
        if (item != null) {
            if (item.getQuantity() > 1) {
                item.decrement();
            } else {
                cart.remove(number);
            }
            ra.addFlashAttribute("msg", "Updated cart for: " + number);
        }
        return "redirect:/products";
    }
}

