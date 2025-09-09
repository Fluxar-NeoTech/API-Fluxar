package org.example.apifluxar.repository;

import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNome(String name);
    Product findProductById(Long id);
}
