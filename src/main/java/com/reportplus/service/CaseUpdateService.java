package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.model.CaseUpdate;
import com.reportplus.repository.CaseRepository;
import com.reportplus.repository.CaseUpdateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseUpdateService {

    private final CaseUpdateRepository repository;
    private final CaseRepository caseRepository;

    public CaseUpdateService(
            CaseUpdateRepository repository,
            CaseRepository caseRepository) {

        this.repository = repository;
        this.caseRepository = caseRepository;
    }

    // Create Update
    public CaseUpdate createUpdate(CaseUpdate update) {

        if (update.getCaseEntity() == null ||
                update.getCaseEntity().getCaseId() == null) {

            throw new RuntimeException("Case is required");
        }

        Case existingCase = caseRepository.findById(
                update.getCaseEntity().getCaseId())
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));

        update.setCaseEntity(existingCase);
        update.setUpdatedAt(LocalDateTime.now());

        return repository.save(update);
    }

    // Get all updates
    public List<CaseUpdate> getAll() {
        return repository.findAll();
    }

    // Get updates by Case
    public List<CaseUpdate> getByCase(Long caseId) {
        return repository.findByCaseEntityCaseId(caseId);
    }

    // Get by ID
    public CaseUpdate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case Update not found"));
    }
}