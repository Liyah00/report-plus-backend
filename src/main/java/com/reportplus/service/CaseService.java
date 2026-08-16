package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.model.Organization;
import com.reportplus.repository.CaseRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseService {

    private final CaseRepository repository;

    private final HaversineService haversineService;


    public CaseService(
            CaseRepository repository,
            HaversineService haversineService
    ) {

        this.repository = repository;

        this.haversineService = haversineService;

    }


    // CREATE CASE
    public Case createCase(Case caseEntity) {

        caseEntity.setCaseStatus("PENDING");

        caseEntity.setCreatedAt(LocalDateTime.now());

        caseEntity.setUpdatedAt(LocalDateTime.now());


        // FIND NEAREST POLICE STATION
        Organization nearestPolice =
                haversineService.findNearestPoliceStation(
                        caseEntity.getLatitude(),
                        caseEntity.getLongitude()
                );


        // ASSIGN CASE TO POLICE STATION
        caseEntity.setAssignedPoliceOrgId(
                nearestPolice.getOrganizationId()
        );


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


    // GET CASES ASSIGNED TO POLICE ORGANIZATION
    public List<Case> getCasesByPoliceOrganization(
            Long organizationId
    ) {

        return repository.findByAssignedPoliceOrgId(
                organizationId
        );

    }


    // UPDATE CASE STATUS
    public Case updateStatus(Long id, String status) {

        Case existingCase =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Case not found"));


        existingCase.setCaseStatus(status);

        existingCase.setUpdatedAt(
                LocalDateTime.now()
        );


        return repository.save(existingCase);

    }


    // DELETE CASE
    public void deleteCase(Long id) {

        Case existingCase =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Case not found"));


        repository.delete(existingCase);

    }


    // GET CITIZEN CASES
    public List<Case> getCitizenCases(
            Long citizenId
    ) {

        return repository.findByCitizenId(
                citizenId
        );

    }

}