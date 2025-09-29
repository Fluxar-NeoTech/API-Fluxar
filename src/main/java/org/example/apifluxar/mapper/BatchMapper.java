package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Unit;
import org.example.apifluxar.service.IndustryService;
import org.example.apifluxar.service.ProductService;
import org.example.apifluxar.service.SectorService;
import org.example.apifluxar.service.UnitService;
import org.springframework.stereotype.Component;

@Component
public class BatchMapper {
    final ProductService productService;
    final UnitService unitService;
    final SectorService sectorService;
    final IndustryService industryService;

    public BatchMapper(UnitService unitService, ProductService productService, SectorService sectorService, IndustryService industryService) {
        this.industryService = industryService;
        this.sectorService = sectorService;
        this.unitService = unitService;
        this.productService = productService;
    }

    // RequestDTO -> Entity
    public Batch batchToMap(BatchRequestDTO dto, Product product, Unit unit) {
        return new Batch(
                dto.getBatchCode(),
                dto.getExpirationDate(),
                dto.getHeight(),
                dto.getLength(),
                dto.getWidth(),
                product,
                unit
        );
    }

//    // Entity -> ResponseCreateDTO
//    public BatchResponseCreateDTO mapToBatchCreate(Batch batch) {
//        Product product = batch.getProduct();
//        AllProductsResponseDTO allProductsResponseDTO = null;
//
//        if (product != null) {
//            allProductsResponseDTO = productService.getAllProductById(product.getId());
//        }
//
//        Unit unit = batch.getUnit();
//        UnitBatchResponseDTO unitBatchResponseDTO = null;
//        if (unit != null) {
//            unitBatchResponseDTO = unitService.getUnitBatchById(unit.getId());
//        }
//
//        return new BatchResponseCreateDTO(
//                batch.getHeight(),
//                batch.getLength(),
//                batch.getBatchCode(),
//                batch.getWidth(),
//                allProductsResponseDTO,
//                unitBatchResponseDTO,
//                batch.getExpirationDate()
//        );
//    }

    //Entity -> ResponseDTO
    public BatchResponseDTO mapToBatch(Batch batch, Product product, Unit unit) {
        BatchResponseDTO responseDTO =  new BatchResponseDTO(
                batch.getBatchCode(),
                batch.getExpirationDate(),
                batch.getHeight(),
                batch.getLength(),
                batch.getWidth(),
                batch.getVolume()
        );

        unit = batch.getUnit();
        UnitResponseDTO unitResponseDTO = null;
        if (unit != null) {
            unitResponseDTO = unitService.getUnitById(unit.getId());
            unitResponseDTO.setIndustry(industryService.getIndustryById(unit.getIndustry().getId()));
        }
        responseDTO.setUnit(unitResponseDTO);

        product = batch.getProduct();
        ProductResponseDTO productResponseDTO = null;
        if (product != null) {
            productResponseDTO = productService.getProductById(product.getId());
            productResponseDTO.setSector(sectorService.getSectorById(product.getSector().getId()));
        }
        responseDTO.setProduct(productResponseDTO);

        return responseDTO;
    }

}
