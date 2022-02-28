package com.lendlease.web.Shop.Controller;

import com.lendlease.web.Shop.Models.Category;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Repo.CategoryRepository;
import com.lendlease.web.Shop.Service.CategoryService;
import com.lendlease.web.Shop.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok().body(productService.findAllProduct());
    }

    @GetMapping("/products/getCategory")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok().body(categoryService.findAllCategory());
    }

    @GetMapping(value = "/products", params = { "id" })
    public ResponseEntity<Product> getItemById(@RequestParam(value = "id") Integer id){
        return ResponseEntity.ok().body(productService.findProductById(id).get());
    }

    @GetMapping(value = "/products", params = { "category" })
    public ResponseEntity<?> getItemByCategory(@RequestParam(value = "category") String category){
        Category categoryObj = categoryService.findCategoryByName(category);
        List<Product> searchResult = productService.findProductByCategory(categoryObj);
        if(searchResult == null) {
            logger.error("getItemByCategory: There is no items under the defined category.");
            return ResponseEntity.ok().body("No products assigned under the category");
        }
        return ResponseEntity.ok().body(searchResult);
    }

    @PostMapping("/products/add")
    public ResponseEntity<Product> saveItem(@RequestBody Product newItem, Authentication auth) {

        newItem.setCreatedTime(new Date());
        newItem.setUpdatedTime(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body((productService.saveProduct(newItem)));
    }

    @PutMapping("/products/update/{id}")
    public ResponseEntity<Product> updateItem(@PathVariable("id") Integer id,@RequestBody Product newItem) {
        newItem.setId(id);
        return ResponseEntity.ok().body(productService.updateProduct(id,newItem));
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
