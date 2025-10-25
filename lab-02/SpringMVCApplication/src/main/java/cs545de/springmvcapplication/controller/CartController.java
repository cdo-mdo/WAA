package cs545de.springmvcapplication.controller;

import cs545de.springmvcapplication.service.CartService;
import cs545de.springmvcapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    @GetMapping
    public String view(Model model, @ModelAttribute("flashMessage") String flashMessage) {
        model.addAttribute("items", cartService.items());
        model.addAttribute("totalQty", cartService.totalQuantity());
        return "cart";
    }

    @PostMapping("/{number}/add")
    public String add(@PathVariable String number, RedirectAttributes ra) {
        var product = productService.findByNumber(number);
        if (product.isPresent()) {
            cartService.add(product.get());
            ra.addFlashAttribute("flashMessage", "Added to cart.");
        } else {
            ra.addFlashAttribute("flashMessage", "Product not found.");
        }
        return "redirect:/cart"; // PRG
    }

    @PostMapping("/{number}/removeOne")
    public String removeOne(@PathVariable String number, RedirectAttributes ra) {
        cartService.removeOne(number);
        ra.addFlashAttribute("flashMessage", "Updated cart.");
        return "redirect:/cart";
    }

    @PostMapping("/{number}/removeAll")
    public String removeAll(@PathVariable String number, RedirectAttributes ra) {
        cartService.removeAll(number);
        ra.addFlashAttribute("flashMessage", "Removed product from cart.");
        return "redirect:/cart";
    }
}

