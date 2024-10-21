package com.helix.ecom.repository;

import com.helix.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p Where " +
            "Lower(p.name) Like Lower(CONCAT('%', :keyword, '%')) OR " +
            "Lower(p.description) Like Lower(CONCAT('%', :keyword, '%')) OR " +
            "Lower(p.brand) Like Lower(CONCAT('%', :keyword, '%')) OR " +
            "Lower(p.category) Like Lower(CONCAT('%', :keyword, '%'))"
    )
    List<Product> searchProducts(String keyword);
}
