package com.lendlease.web.Shop.Repo;

import com.lendlease.web.Shop.Models.Category;
import com.lendlease.web.Shop.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

}
