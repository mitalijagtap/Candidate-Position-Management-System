package com.cpms.controller;

import com.cpms.dto.CandidateDTO;
import com.cpms.service.CandidateService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/create")
    public ResponseEntity<CandidateDTO> create(@RequestBody @Valid CandidateDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.create(dto));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateCandidatePartially(
            @PathVariable Long id,
            @RequestBody JsonNode updates
    ) {
        try {
            CandidateDTO updated = candidateService.partialUpdateCandidate(id, updates);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } catch (JsonMappingException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid JSON structure for update: " + e.getOriginalMessage());
        }

    }

}
