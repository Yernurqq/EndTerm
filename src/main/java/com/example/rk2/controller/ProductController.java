package com.example.rk2.controller;

import com.example.rk2.entity.Product;
import com.example.rk2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    // Главная страница
    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "addProduct";
    }

    // Фильтрация продуктов
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) Double minPrice,
                         @RequestParam(required = false) Double maxPrice,
                         Model model) {
        List<Product> products;
        if (category != null) {
            products = productService.filterByCategory(category);
        } else if (minPrice != null && maxPrice != null) {
            products = productService.filterByPriceRange(minPrice, maxPrice);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        return "addProduct";
    }

    @GetMapping("/addProduct")
    public String showAddProductForm() {
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam String category,
                             @RequestParam double price) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        productService.saveProduct(product);
        return "redirect:/";
    }
}
