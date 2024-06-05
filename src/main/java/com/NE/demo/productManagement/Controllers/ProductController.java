package com.NE.demo.productManagement.Controllers;

import com.NE.demo.productManagement.Models.Product;
import com.NE.demo.productManagement.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/product")
public class ProductController {

    @Autowired
    public ProductController(ProductService productService){
    }

    @PostMapping("/register")
    public void registerProduct(@RequestBody Product product){
        ProductService.addNewProduct(product);
    }
}
