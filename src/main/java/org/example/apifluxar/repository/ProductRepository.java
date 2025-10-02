package org.example.apifluxar.repository;

import org.example.apifluxar.dto.products.ProductResponseDTO;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//   List<Product> findByName(String name);

   //seleciona todos os produtos pela unidade e pelo setor do usuário
   @Query("SELECT DISTINCT p FROM Batch b " +
           "JOIN Product p ON p.id = b.product.id " +
           "JOIN Unit u ON u.id = b.unit.id " +
           "JOIN Employee e ON u.id = e.unit.id " +
           "WHERE e.id = :employeeId")
   List<Product> findAllProductRegistered(@Param("employeeId") Long id);


   //seleciona sku do lote e id do produto
   @Query("SELECT b.batchCode FROM Batch b\n" +
           "JOIN Product p ON p.id = b.product.id\n" +
           "JOIN Unit u ON u.id = b.unit.id\n" +
           "JOIN Employee e ON u.id = e.unit.id\n" +
           "WHERE p.id =:productId")
   List<String> findBatchByProduct(@Param("productId") Long id);
}
