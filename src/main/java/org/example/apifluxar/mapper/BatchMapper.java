package org.example.apifluxar.mapper;

import org.example.apifluxar.service.IndustryService;
import org.example.apifluxar.service.ProductService;
import org.example.apifluxar.service.SectorService;
import org.example.apifluxar.service.UnitService;
import org.springframework.stereotype.Component;


//Provavelmente vou tirar dps
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
}
