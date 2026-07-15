package com.reportplus.repository;

import com.reportplus.model.CaseUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseUpdateRepository extends JpaRepository<CaseUpdate, Long> {

    List<CaseUpdate> findByCaseEntityCaseId(Long caseId);

}