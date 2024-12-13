package com.deconfort.tiendita.repository;

import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategory(String category);
}
