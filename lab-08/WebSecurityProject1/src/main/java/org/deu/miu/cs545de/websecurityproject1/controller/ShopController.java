package org.deu.miu.cs545de.websecurityproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/shop")
    public String shop() {
        return "Welcome to the shop (public)";
    }

    @GetMapping("/orders")
    public String orders() {
        return "Orders - employees only";
    }

    @GetMapping("/payment")
    public String payment() {
        return "Payments - finance department only";
    }

}
