package com.reportplus.repository;

import com.reportplus.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Long> {

    List<Case> findByCitizenId(Long citizenId);

    List<Case> findByAssignedPoliceOrgId(Long assignedPoliceOrgId);

}