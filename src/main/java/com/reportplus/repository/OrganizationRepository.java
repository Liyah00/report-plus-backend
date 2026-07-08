package com.reportplus.repository;

import com.reportplus.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findByOrganizationTypeAndStatus(
            String organizationType,
            String status
    );

}