package org.example.apifluxar.mapper;

import jakarta.persistence.Column;
import org.example.apifluxar.dto.*;
import org.example.apifluxar.model.Batch;
import org.example.apifluxar.model.Employee;
import org.example.apifluxar.model.Product;
import org.springframework.stereotype.Component;

@Component
public class BatchMapper {
    public Batch mapToBatch(BatchRequestDTO dto) {
        return new Batch(
                dto.getIdLote(),
                dto.getValidade(),
                dto.getAltura(),
                dto.getComprimento(),
                dto.getLargura()
        );
    }

    public BatchResponseCreateDTO mapToBatchResponseCreateDTO(Batch batch, Long productId, Long unitId) {
        BatchResponseCreateDTO dto = new BatchResponseCreateDTO(
                batch.getIdLote(),
                batch.getValidade(),
                batch.getAltura(),
                batch.getComprimento(),
                batch.getLargura(),
                batch.getVolume()
        );
        dto.setProduct(productId);
        dto.setUnit(unitId);
        return dto;
    }

    public BatchResponseDTO mapToBatchResponseDTO(Batch batch) {
        BatchResponseDTO dto = new BatchResponseDTO(
                batch.getIdLote(),
                batch.getValidade(),
                batch.getAltura(),
                batch.getComprimento(),
                batch.getLargura(),
                batch.getVolume()
        );
        return dto;
    }
}
