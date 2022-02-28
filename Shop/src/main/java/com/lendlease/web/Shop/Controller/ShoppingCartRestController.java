package com.lendlease.web.Shop.Controller;

import com.lendlease.web.Shop.Models.CartItem;
import com.lendlease.web.Shop.Models.User;
import com.lendlease.web.Shop.Service.ShoppingCartService;
import com.lendlease.web.Shop.Service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartRestController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);

    @GetMapping("/cart/list")
    public ResponseEntity<?> showShoppingCart(Authentication auth){

        User user = userService.findUserByUsername(auth.getName());
        List<CartItem> cartItems= cartService.listCartItems(user);

        return ResponseEntity.ok().body(cartItems);
    }

    @PostMapping("/cart/add/{pid}/{qty}")
    public ResponseEntity<?> addProductToCart(@PathVariable("pid") Integer productId, @PathVariable("qty") Integer quantity,
                                              Authentication auth){

        User user = userService.findUserByUsername(auth.getName());

        if(user == null){
            logger.error("addProductToCart: No logged in user detected.");
            return ResponseEntity.ok().body("You must login to add this product to your shopping cart.");
        }

        Integer addedQuantity = cartService.addProduct(productId, quantity, user);

        return ResponseEntity.ok().body(addedQuantity + "Item(s) of this product were added to your shopping cart.");
    }

    @PostMapping("/cart/update/{pid}/{qty}")
    public String updateQuantity(@PathVariable("pid") Integer productId, @PathVariable("qty") Integer quantity,
                                 Authentication auth){
        User user = userService.findUserByUsername(auth.getName());

        if(user == null){
            logger.error("updateQuantity: No logged in user detected.");
            return "You must login to update the quantity.";
        }

        float subTotal = cartService.updateQuantity(productId, quantity, user);

        return String.valueOf(subTotal);
    }

    @DeleteMapping("/cart/delete/{pid}")
    public String deleteProductFromCart(@PathVariable("pid") Integer productId, Authentication auth){

        User user = userService.findUserByUsername(auth.getName());

        if(user == null){
            logger.error("deleteProductFromCart: No logged in user detected.");
            return "You must login to delete product from your shopping cart.";
        }

        cartService.deleteProductFromCart(productId, user);

        return "The product is deleted from your shopping cart.";
    }
}
