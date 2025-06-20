package com.cpms.repository;

import com.cpms.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    boolean existsByEmail(String email);

}
