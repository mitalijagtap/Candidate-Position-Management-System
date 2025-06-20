package com.cpms.repository;

import com.cpms.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {


    boolean existsByPositionName(String name);
    boolean existsByIdIn(List<Long> ids);

}
