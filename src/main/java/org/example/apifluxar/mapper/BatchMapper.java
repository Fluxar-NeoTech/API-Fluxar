package org.example.apifluxar.mapper;

import org.example.apifluxar.dto.*;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Industry;
import org.example.apifluxar.model.Product;
import org.example.apifluxar.model.Unit;
import org.springframework.stereotype.Component;

@Component
public class BatchMapper {

    // RequestDTO -> Entity
    public Batch mapToBatch(BatchRequestDTO dto, Product product, Unit unit) {
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
        ProductResponseDTO productDTO = new ProductResponseDTO(
                batch.getProduto().getNome(),
                batch.getProduto().getTipo()
        );

        Unit unit = batch.getUnidade();
        UnitResponseDTO unitDTO = new UnitResponseDTO(
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
                unit.getIndustry().getCnpj()
        );
        UnitResponseDTO unitResponseDTO = new UnitResponseDTO(
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
        responseDTO.setProduct(productResponseDTO);
        return responseDTO;
    }

}
