package com.cpms.service;

import com.cpms.dto.PositionDTO;
import com.cpms.entity.Position;
import com.cpms.mapper.PositionMapper;
import com.cpms.repository.PositionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PositionServiceImlp implements PositionService{

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    public PositionServiceImlp(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public PositionDTO create(PositionDTO dto) {
        if (positionRepository.existsByPositionName(dto.getPositionName())) {
            throw new IllegalArgumentException("Position name already exists");
        }
        Position position = positionMapper.toEntity(dto);
        return positionMapper.toDTO(positionRepository.save(position));
    }

    @Override
    public Page<PositionDTO> getAll(Pageable pageable) {
        return positionRepository.findAll(pageable).map(positionMapper::toDTO);
    }


    @Override
    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));

        if (!position.getCandidates().isEmpty()) {
            throw new RuntimeException("Cannot delete position linked to candidates");
        }

        positionRepository.delete(position);
    }




}
