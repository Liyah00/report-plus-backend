package com.reportplus.repository;

import com.reportplus.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {

    List<Evidence> findByCaseEntity_CaseId(Long caseId);

}