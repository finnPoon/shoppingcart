package com.lendlease.web.Shop.Service;

import com.lendlease.web.Shop.Models.CartItem;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Models.User;
import com.lendlease.web.Shop.Repo.CartItemRepository;
import com.lendlease.web.Shop.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<CartItem> listCartItems(User user){
        return cartRepo.findByUser(user);
    }

    public Integer addProduct(Integer productId, Integer quantity, User user){
        Integer addedQuantity = quantity;
        Product product = productRepo.findById(productId).get();

        CartItem cartItem = cartRepo.findByUserAndProduct(user,product);

        if(cartItem != null){
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }
        else{
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUser(user);
        }

        cartRepo.save(cartItem);

        return addedQuantity;
    }

    public float updateQuantity(Integer id, Integer quantity, User user){
        Integer userId = user.getId();
        cartRepo.updateQuantity(quantity,id, userId);

        Product product = productRepo.findById(id).get();

        float subTotal = product.getPrice() * quantity;
        return subTotal;
    }

    public void deleteProductFromCart(Integer productId, User user){

        Integer userId = user.getId();
        cartRepo.deleteFromCart(userId, productId);
    }
}
