package com.example.demo.controllers;

import org.springframework.stereotype.Controller;


import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BuyController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/buy")
    public String buyProduct(@RequestParam("productID") Long theId, Model theModel) {
        Optional<Product> productToBuy = productRepository.findById(theId);

        if (productToBuy.isPresent()) {
            Product product = productToBuy.get();

            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productRepository.save(product);

                return "/success";
            } else {
                return "/failure";
            }
        } else {
            return "/failure";
        }
    }
}
