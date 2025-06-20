package com.cpms.service;

import com.cpms.dto.PositionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface PositionService {

    PositionDTO create(PositionDTO dto);

    Page<PositionDTO> getAll(Pageable pageable);

    void deletePosition(Long id);

}
