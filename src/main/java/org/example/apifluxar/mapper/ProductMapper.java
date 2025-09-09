package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.ProductRequestDTO;
import org.example.apifluxar.dto.ProductResponseDTO;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToProduct(ProductRequestDTO productRequestDTO) {
        Employee employee = new Employee();

        Product product = new Product(
                productRequestDTO.getNome(),
                productRequestDTO.getTipo(),
                employee.getSetor());

        return product;
    }

    public ProductResponseDTO mapToProductResponseDTO(Product product) {

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getNome(),
                product.getTipo()
        );

        return productResponseDTO;
    }
}
