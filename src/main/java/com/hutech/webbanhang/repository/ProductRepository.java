package com.hutech.webbanhang.repository;

import com.hutech.webbanhang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
// Các phương thức tùy biến…

}
