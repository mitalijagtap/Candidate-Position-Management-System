package com.cpms.service;

import com.cpms.dto.CandidateDTO;
import com.cpms.entity.Candidate;
import com.cpms.entity.Position;
import com.cpms.exception.ResourceNotFoundException;
import com.cpms.mapper.CandidateMapper;
import com.cpms.repository.CandidateRepository;
import com.cpms.repository.PositionRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService{

    private final CandidateRepository candidateRepository;
    private final PositionRepository positionRepository;
    private final CandidateMapper candidateMapper;
    private final ObjectMapper objectMapper;

    public CandidateServiceImpl(CandidateRepository candidateRepository, PositionRepository positionRepository, CandidateMapper candidateMapper, ObjectMapper objectMapper) {
        this.candidateRepository = candidateRepository;
        this.positionRepository = positionRepository;
        this.candidateMapper = candidateMapper;
        this.objectMapper = objectMapper;
    }


    @Override
    public CandidateDTO create(CandidateDTO dto) {
        if (candidateRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already used");
        }

        if (Period.between(dto.getDob(), LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Candidate must be 18 or older");
        }

        List<Position> positions = positionRepository.findAllById(dto.getPositionIds());
        if (positions.size() != dto.getPositionIds().size()) {
            throw new IllegalArgumentException("One or more position IDs are invalid");
        }

        Candidate candidate = candidateMapper.toEntity(dto);
        candidate.setPositions(positions);

        Candidate savedCandidate = candidateRepository.save(candidate);
        return candidateMapper.toDTO(savedCandidate);
}

    @Override
    public CandidateDTO partialUpdateCandidate(Long id, JsonNode updates) throws JsonMappingException{

        Candidate candidate=candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        Candidate updatedCandidate=objectMapper.updateValue(candidate,updates);
        updatedCandidate=candidateRepository.save(updatedCandidate);
        return candidateMapper.toDTO(updatedCandidate);
    }


}
