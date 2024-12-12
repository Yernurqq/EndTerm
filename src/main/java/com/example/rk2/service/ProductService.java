package com.example.rk2.service;

import com.example.rk2.entity.Product;
import com.example.rk2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> filterByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
