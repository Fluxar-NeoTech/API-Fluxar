package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.products.ProductRequestDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.model.Employee;
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

    public ProductResponseDTO mapToProductResponseDTO(Product product) {
        Sector setor = product.getSector();
        SectorResponseDTO sectorResponseDTO = null;

        if (setor != null) {
            sectorResponseDTO = sectorService.getSectorById(setor.getId());
        }

        return new ProductResponseDTO(
                product.getName(),
                product.getType(),
                sectorResponseDTO
        );
    }
}
