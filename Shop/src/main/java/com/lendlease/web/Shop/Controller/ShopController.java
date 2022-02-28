package com.lendlease.web.Shop.Controller;

import com.lendlease.web.Shop.Models.Category;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Service.CategoryService;
import com.lendlease.web.Shop.Service.ProductService;
import com.lendlease.web.Shop.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, Authentication auth){

        return "shopping_cart";
    }

    @GetMapping("/product")
    public String showProductsPage(Model model, @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                   @AuthenticationPrincipal Authentication authentication){
        List<Product> productItems= null;
        if(categoryId == null){
            productItems = productService.findAllProduct();
        }
        else{
            Category searchCategory = categoryService.findCategoryById(categoryId).get();
            productItems = productService.findProductByCategory(searchCategory);
        }

        List<Category> categoryItems = categoryService.findAllCategory();
        model.addAttribute("productItems", productItems);
        model.addAttribute("categoryItems", categoryItems);
        return "product/product_page";
    }

    @GetMapping("/products/new")
    public String createNewProductPage(Model model, @AuthenticationPrincipal Authentication authentication){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/create_product";
    }

    @GetMapping("/products/edit")
    public String editProductPage(Model model, @AuthenticationPrincipal Authentication authentication){
        /*Product product = new Product();
        model.addAttribute("product", product);*/
        return "product/edit_product";
    }

    @GetMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }
}
