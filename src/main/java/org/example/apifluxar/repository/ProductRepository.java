package org.example.apifluxar.repository;

import org.example.apifluxar.dto.product.ProductResponseDTO;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

   @Query("SELECT DISTINCT p.id AS id, p.name AS name, p.type AS type " +
           "FROM Product p " +
           "JOIN p.sector s " +
           "JOIN Employee f ON f.sector.id = s.id " +
           "WHERE f.id = :employeeId")
   List<ProductProjection> findAllProductRegistered(@Param("employeeId") Long id);


   @Query("SELECT DISTINCT b.batchCode FROM Batch b\n" +
           "JOIN Product p ON p.id = b.product.id\n" +
           "JOIN Unit u ON u.id = b.unit.id\n" +
           "JOIN Employee e ON u.id = e.unit.id\n" +
           "WHERE p.id =:productId")
   List<String> findBatchByProduct(@Param("productId") Long id);
}
