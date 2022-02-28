package com.lendlease.web.Shop.Service;

import com.lendlease.web.Shop.Models.Category;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> findAllCategory() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepo.findById(id);
    }

    public Category findCategoryByName(String name) {
        return categoryRepo.findByName(name);
    }
}
