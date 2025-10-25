package cs545de.springmvcapplication.controller;

import cs545de.springmvcapplication.model.Product;
import cs545de.springmvcapplication.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService = new ProductService();

    @GetMapping
    public String list(Model model, @ModelAttribute("flashMessage") String flashMessage) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("product", new Product()); // form-backing object
        return "products";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") Product product,
                      BindingResult result,
                      RedirectAttributes ra) {

        if (productService.exists(product.getProductNumber())) {
            result.rejectValue("productNumber", "exists", "Product number already exists");
        }

        if (result.hasErrors()) {
            // store validation errors for redirect (PRG with flash attributes)
            ra.addFlashAttribute("org.springframework.validation.BindingResult.product", result);
            ra.addFlashAttribute("product", product);
            return "redirect:/products";
        }

        productService.save(product);
        ra.addFlashAttribute("flashMessage", "Product added.");
        return "redirect:/products"; // <-- PRG
    }

    @PostMapping("/{number}/delete")
    public String delete(@PathVariable String number, RedirectAttributes ra) {
        productService.delete(number);
        ra.addFlashAttribute("flashMessage", "Product removed.");
        return "redirect:/products"; // <-- PRG
    }
}
