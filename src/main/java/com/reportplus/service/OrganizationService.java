package com.reportplus.service;

import com.reportplus.model.Organization;
import com.reportplus.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    // CREATE ORGANIZATION
    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    // GET ALL
    public List<Organization> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public Organization getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    // GET ACTIVE HOSPITALS
    public List<Organization> getActiveHospitals() {
        return repository.findByOrganizationTypeAndStatus(
                "HOSPITAL",
                "ACTIVE"
        );
    }
}