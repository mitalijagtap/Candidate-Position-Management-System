package com.cpms.service;

import com.cpms.dto.CandidateDTO;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;

public interface CandidateService {


    CandidateDTO create(@Valid CandidateDTO dto);

    CandidateDTO partialUpdateCandidate(Long id, JsonNode updates)throws JsonMappingException;


}
