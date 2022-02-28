package com.lendlease.web.Shop.Service;

import com.lendlease.web.Shop.Models.Category;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepo.findById(id);
    }

    public List<Product> findProductByCategory(Category category) {
        return productRepo.findByCategory(category);
    }

    public Product saveProduct(Product newProduct) {
        Product product = productRepo.save(newProduct);
        return product;
    }

    public Product updateProduct(Integer id,Product product) {

        Optional<Product> retrievedProduct=productRepo.findById(id);

        retrievedProduct.ifPresent(oldProduct -> {
            product.setCreatedTime(oldProduct.getCreatedTime());
            product.setUpdatedTime(new Date());
        });

        if(retrievedProduct.isEmpty())
            try {
                throw new Exception("Product not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        productRepo.save(product);
        return productRepo.findById(id).get();
    }

    public Product deleteProduct(Integer id) {

        Optional<Product> retrievedProduct = productRepo.findById(id);
        if(retrievedProduct == null)
            try {
                throw new Exception("Product not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        productRepo.deleteById(id);
        return retrievedProduct.get();
    }
}
