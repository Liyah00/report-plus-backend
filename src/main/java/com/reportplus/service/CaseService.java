package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.repository.CaseRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseService {

    private final CaseRepository repository;


    public CaseService(CaseRepository repository) {
        this.repository = repository;
    }


    // CREATE CASE
    public Case createCase(Case caseEntity) {

        caseEntity.setCaseStatus("PENDING");
        caseEntity.setCreatedAt(LocalDateTime.now());
        caseEntity.setUpdatedAt(LocalDateTime.now());

        return repository.save(caseEntity);
    }



    // GET ALL CASES
    public List<Case> getAllCases() {

        return repository.findAll();
    }



    // GET CASE BY ID
    public Case getCaseById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));
    }



    // UPDATE CASE STATUS
    public Case updateStatus(Long id, String status) {

        Case existingCase = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));


        existingCase.setCaseStatus(status);
        existingCase.setUpdatedAt(LocalDateTime.now());


        return repository.save(existingCase);
    }



    // DELETE CASE
    public void deleteCase(Long id) {

        Case existingCase = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));

        repository.delete(existingCase);
    }

}