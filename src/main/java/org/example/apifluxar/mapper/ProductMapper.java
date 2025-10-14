package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.product.ProductRequestDTO;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.service.SectorService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    final SectorService sectorService;

    public ProductMapper(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    public Product mapToProduct(ProductRequestDTO productRequestDTO, Sector sector) {
        Product product = new Product(
                productRequestDTO.getName(),
                productRequestDTO.getType(),
                sector);

        return product;
    }
}
