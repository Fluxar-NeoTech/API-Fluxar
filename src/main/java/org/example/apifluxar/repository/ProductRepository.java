package org.example.apifluxar.repository;

import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
   List<Product> findByName(String name);
}
