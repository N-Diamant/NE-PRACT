package com.NE.demo.productManagement.Services;

import com.NE.demo.productManagement.Models.Product;
import com.NE.demo.productManagement.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static ProductRepository productRepository = null;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        ProductService.productRepository = productRepository;
    }

    public static void  addNewProduct(Product product) {
         productRepository.save(product);
    }
}

