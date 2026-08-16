package com.reportplus.repository;

import com.reportplus.model.PF3Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PF3FormRepository extends JpaRepository<PF3Form, Long> {

    Optional<PF3Form> findByPf3Code(String pf3Code);

    Optional<PF3Form> findByRequest_RequestId(Long requestId);

    List<PF3Form> findByRequest_CitizenId(Long citizenId);

    // Get PF3 forms assigned to a specific hospital
    List<PF3Form> findByHospitalId(Long hospitalId);
}