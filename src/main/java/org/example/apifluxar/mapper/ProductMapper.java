package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.ProductRequestDTO;
import org.example.apifluxar.dto.ProductResponseDTO;
import org.example.apifluxar.dto.SectorResponseDTO;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Sector;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToProduct(ProductRequestDTO productRequestDTO, Sector sector) {
        Employee employee = new Employee();

        Product product = new Product(
                productRequestDTO.getNome(),
                productRequestDTO.getTipo(),
                sector);

        return product;
    }

    public ProductResponseDTO mapToProductResponseDTO(Product product) {
        Sector setor = product.getSetor();

        SectorResponseDTO setorDTO = null;
        if (setor != null) {
            setorDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );
        }

        return new ProductResponseDTO(
                product.getNome(),
                product.getTipo(),
                setorDTO
        );
    }
}
