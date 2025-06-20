package com.cpms.mapper;

import com.cpms.dto.PositionDTO;
import com.cpms.entity.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionDTO toDTO(Position position);
    Position toEntity(PositionDTO dto);

    List<PositionDTO> toDTOList(List<Position> positions);

}
