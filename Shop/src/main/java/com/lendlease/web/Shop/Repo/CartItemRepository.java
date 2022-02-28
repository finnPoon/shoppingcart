package com.lendlease.web.Shop.Repo;

import com.lendlease.web.Shop.Models.CartItem;
import com.lendlease.web.Shop.Models.Product;
import com.lendlease.web.Shop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndProduct(User user, Product product);

    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id = ?2 AND c.user.id = ?3")
    @Modifying
    public void updateQuantity(Integer quantity, Integer id, Integer userId);

    @Query("DELETE FROM CartItem c WHERE c.user.id = ?1 AND c.product.id = ?2")
    @Modifying
    public void deleteFromCart(Integer userId, Integer productId);
}
