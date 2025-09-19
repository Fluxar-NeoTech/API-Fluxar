package org.example.apifluxar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apifluxar.dto.*;
import org.example.apifluxar.model.*;
import org.example.apifluxar.repository.CapacityHistporyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapacityHistoryService {
    final CapacityHistporyRepository capacityHistporyRepository;
    final ObjectMapper objectMapper;

    public CapacityHistoryService(CapacityHistporyRepository capacityHistoryRepository, ObjectMapper objectMapper) {
        this.capacityHistporyRepository = capacityHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public CapacityHistoryResponseDTO getCapacityHistoryById(Long id) {
        CapacityHistory capacityHistory = capacityHistporyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CapacityHistoryResponseDTO dto = new CapacityHistoryResponseDTO(
                capacityHistory.getCapacidadeTotal(),
                capacityHistory.getDataCompleta()
        );
        Sector setor = capacityHistory.getSector();
        if (setor != null) {
            SectorResponseDTO sectorDTO = new SectorResponseDTO(
                    setor.getId(),
                    setor.getNome(),
                    setor.getDescricao()
            );
            dto.setSector(sectorDTO);
        }
        Unit unit = capacityHistory.getUnidade();
        if (unit != null) {
            UnitResponseDTO unitDTO = new UnitResponseDTO(
                    unit.getNome(),
                    unit.getCep(),
                    unit.getRua(),
                    unit.getCidade(),
                    unit.getEstado(),
                    unit.getNumero(),
                    unit.getBairro()
            );
            dto.setUnidade(unitDTO);
        }
        Product product = capacityHistory.getProduto();
        if (product != null) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                product.getNome(),
                product.getTipo()
            );
            dto.setProduto(productResponseDTO);
        }
        Batch batch = capacityHistory.getLote();
        if (batch != null) {
            BatchResponseDTO batchResponseDTO = new BatchResponseDTO(
                batch.getIdLote(),
                batch.getValidade(),
                batch.getAltura(),
                batch.getComprimento(),
                batch.getLargura(),
                batch.getVolume()
            );
        }
        return dto;
    }

    public List<CapacityHistoryResponseDTO> getCapacityHistoryByLote(Long loteId) {
        List<CapacityHistory> capacityHistory = capacityHistporyRepository.findByLote(loteId);
        List<CapacityHistoryResponseDTO> dtos = new ArrayList<>();
        for(CapacityHistory capacityHistoryItem : capacityHistory) {
            CapacityHistoryResponseDTO dto = new CapacityHistoryResponseDTO(
                    capacityHistoryItem.getCapacidadeTotal(),
                    capacityHistoryItem.getDataCompleta()
            );
            Sector setor = capacityHistoryItem.getSector();
            if (setor != null) {
                SectorResponseDTO sectorDTO = new SectorResponseDTO(
                        setor.getId(),
                        setor.getNome(),
                        setor.getDescricao()
                );
                dto.setSector(sectorDTO);
            }
            Unit unit = capacityHistoryItem.getUnidade();
            if (unit != null) {
                UnitResponseDTO unitDTO = new UnitResponseDTO(
                        unit.getNome(),
                        unit.getCep(),
                        unit.getRua(),
                        unit.getCidade(),
                        unit.getEstado(),
                        unit.getNumero(),
                        unit.getBairro()
                );
                dto.setUnidade(unitDTO);
            }
            Product product = capacityHistoryItem.getProduto();
            if (product != null) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                        product.getNome(),
                        product.getTipo()
                );
                dto.setProduto(productResponseDTO);
            }
            Batch batch = capacityHistoryItem.getLote();
            if (batch != null) {
                BatchResponseDTO batchResponseDTO = new BatchResponseDTO(
                        batch.getIdLote(),
                        batch.getValidade(),
                        batch.getAltura(),
                        batch.getComprimento(),
                        batch.getLargura(),
                        batch.getVolume()
                );
            }
            dtos.add(dto);
        }
        return dtos;
    }

    public List<CapacityHistoryResponseDTO> getCapacityHistoryBySectorAndProdutoAndUnidade(Long setorId,Long produtoId,Long unidadeId) {
        List<CapacityHistory> capacityHistory = capacityHistporyRepository.findByProdutoAndSectorAndAndUnidade(produtoId, setorId, unidadeId);
        List<CapacityHistoryResponseDTO> dtos = new ArrayList<>();
        for(CapacityHistory capacityHistoryItem : capacityHistory) {
            CapacityHistoryResponseDTO dto = new CapacityHistoryResponseDTO(
                    capacityHistoryItem.getCapacidadeTotal(),
                    capacityHistoryItem.getDataCompleta()
            );
            Sector setor = capacityHistoryItem.getSector();
            if (setor != null) {
                SectorResponseDTO sectorDTO = new SectorResponseDTO(
                        setor.getId(),
                        setor.getNome(),
                        setor.getDescricao()
                );
                dto.setSector(sectorDTO);
            }
            Unit unit = capacityHistoryItem.getUnidade();
            if (unit != null) {
                UnitResponseDTO unitDTO = new UnitResponseDTO(
                        unit.getNome(),
                        unit.getCep(),
                        unit.getRua(),
                        unit.getCidade(),
                        unit.getEstado(),
                        unit.getNumero(),
                        unit.getBairro()
                );
                dto.setUnidade(unitDTO);
            }
            Product product = capacityHistoryItem.getProduto();
            if (product != null) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                        product.getNome(),
                        product.getTipo()
                );
                dto.setProduto(productResponseDTO);
            }
            Batch batch = capacityHistoryItem.getLote();
            if (batch != null) {
                BatchResponseDTO batchResponseDTO = new BatchResponseDTO(
                        batch.getIdLote(),
                        batch.getValidade(),
                        batch.getAltura(),
                        batch.getComprimento(),
                        batch.getLargura(),
                        batch.getVolume()
                );
            }
            dtos.add(dto);
        }
        return dtos;
    }

    public int deleteCapacityHistoryByIdLote(Long idLote) {
        int deleteQuatidade = capacityHistporyRepository.deleteByLoteId(idLote);
        if (deleteQuatidade == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return deleteQuatidade;
    }

    public int deleteCapacityHistoryByIdSetor(Long idSetor) {
        int deleteQuatidade = capacityHistporyRepository.deleteBySector(idSetor);
        if (deleteQuatidade == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return deleteQuatidade;
    }

    public int deleteCapacityHistoryByIdUnidade(Long idUnidade) {
        int deleteQuatidade = capacityHistporyRepository.deleteByUnidade(idUnidade);
        if (deleteQuatidade == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return deleteQuatidade;
    }
}
