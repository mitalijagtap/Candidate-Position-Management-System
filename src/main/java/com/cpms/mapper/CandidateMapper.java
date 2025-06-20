package com.cpms.mapper;

import com.cpms.dto.CandidateDTO;
import com.cpms.entity.Candidate;
import com.cpms.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PositionMapper.class})
public interface CandidateMapper {

    @Mapping(source = "positions", target = "positionIds", qualifiedByName = "mapPositionListToIds")
    CandidateDTO toDTO(Candidate candidate);

    @Mapping(source = "positionIds", target = "positions", ignore = true) // positions set manually in service
    Candidate toEntity(CandidateDTO dto);

    @Named("mapPositionListToIds")
    default List<Long> mapPositionListToIds(List<Position> positions) {
        return positions == null ? new ArrayList<>() :
                positions.stream().map(Position::getId).collect(Collectors.toList());
    }
}
