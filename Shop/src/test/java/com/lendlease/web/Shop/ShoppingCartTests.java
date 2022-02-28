package com.lendlease.web.Shop;

import com.lendlease.web.Shop.Models.CartItem;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Models.User;
import com.lendlease.web.Shop.Repo.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem(){
        Product product = entityManager.find(Product.class, 1);
        User user = entityManager.find(User.class, 1);

        CartItem newItem = new CartItem();
        newItem.setUser(user);
        newItem.setProduct(product);
        newItem.setQuantity(2);

        CartItem saveCartItem = cartRepo.save(newItem);

        assertTrue(saveCartItem.getId() > 0);
    }

    @Test
    public void testGetCartItemsByCustomer(){
        User user = new User();
        user.setId(2);

        List<CartItem> cartItems = cartRepo.findByUser(user);

        assertEquals(2, cartItems.size());
    }

}
