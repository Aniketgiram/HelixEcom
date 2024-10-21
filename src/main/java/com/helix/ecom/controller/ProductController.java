package com.helix.ecom.controller;

import com.helix.ecom.model.Product;
import com.helix.ecom.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String greet(){
        return "Hello World";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductByID(@PathVariable int productId){
        return new ResponseEntity<Product>(productService.getProductBYID(productId), HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(name = "keyword", required = false) String keyword){
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product p = productService.saveProduct(product, imageFile);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        Product p = productService.getProductBYID(productId);
        if(p != null) {
            productService.deleteProduct(productId);
            return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Failed to delete, Product not Found", HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId, @RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product p = productService.getProductBYID(productId);
            if(p != null){
                productService.updateProduct(productId, product, imageFile);
                return new ResponseEntity<>("Product Updated", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Product Not Found", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int productId){
        return new ResponseEntity<>(productService.getProductBYID(productId).getImageData(), HttpStatus.OK);
    }
}
