package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.batch.BatchRequestDTO;
import org.example.apifluxar.dto.batch.BatchResponseCreateDTO;
import org.example.apifluxar.dto.batch.BatchResponseDTO;
import org.example.apifluxar.dto.industry.IndustryResponseDTO;
import org.example.apifluxar.dto.products.AllProductsResponseDTO;
import org.example.apifluxar.dto.products.ProductResponseDTO;
import org.example.apifluxar.dto.sector.SectorResponseDTO;
import org.example.apifluxar.dto.unit.UnitBatchResponseDTO;
import org.example.apifluxar.dto.unit.UnitResponseDTO;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Sector;
import org.example.apifluxar.model.Unit;
import org.springframework.stereotype.Component;

@Component
public class BatchMapper {

    // RequestDTO -> Entity
    public Batch batchToMap(BatchRequestDTO dto, Product product, Unit unit) {
        return new Batch(
                dto.getIdLote(),
                dto.getValidade(),
                dto.getAltura(),
                dto.getComprimento(),
                dto.getLargura(),
                product,
                unit
        );
    }

    // Entity -> ResponseCreateDTO
    public BatchResponseCreateDTO mapToBatchCreate(Batch batch) {
        AllProductsResponseDTO productDTO = new AllProductsResponseDTO(
                batch.getProduto().getNome(),
                batch.getProduto().getTipo()
        );

        Unit unit = batch.getUnidade();
        UnitBatchResponseDTO unitDTO = new UnitBatchResponseDTO(
                unit.getNome(),
                unit.getCep(),
                unit.getRua(),
                unit.getCidade(),
                unit.getEstado(),
                unit.getNumero(),
                unit.getBairro()
        );

        return new BatchResponseCreateDTO(
                batch.getAltura(),
                batch.getComprimento(),
                batch.getIdLote(),
                batch.getLargura(),
                productDTO,
                unitDTO,
                batch.getValidade()
        );
    }

    //Entity -> ResponseDTO
    public BatchResponseDTO mapToBatch(Batch batch, Product product, Unit unit) {
        BatchResponseDTO responseDTO =  new BatchResponseDTO(
                batch.getIdLote(),
                batch.getValidade(),
                batch.getAltura(),
                batch.getComprimento(),
                batch.getLargura(),
                batch.getVolume()
        );
        IndustryResponseDTO industry = new IndustryResponseDTO(
                unit.getIndustry().getNome(),
                unit.getIndustry().getCnpj(),
                unit.getIndustry().getId()
        );

        UnitResponseDTO unitResponseDTO = new UnitResponseDTO(
                unit.getId(),
                unit.getNome(),
                unit.getCep(),
                unit.getRua(),
                unit.getCidade(),
                unit.getEstado(),
                unit.getNumero(),
                unit.getBairro(),
                industry
        );

        unitResponseDTO.setId(unit.getId());
        responseDTO.setUnit(unitResponseDTO);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getNome(),
                product.getTipo()

        );

        SectorResponseDTO sectorResponseDTO = new SectorResponseDTO(
                product.getSetor().getId(),
                product.getSetor().getNome(),
                product.getSetor().getDescricao()
        );
        productResponseDTO.setSetor(sectorResponseDTO);
        responseDTO.setProduct(productResponseDTO);

        return responseDTO;
    }

}
