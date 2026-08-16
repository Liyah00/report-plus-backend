package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.model.PF3Form;
import com.reportplus.model.User;
import com.reportplus.repository.CaseRepository;
import com.reportplus.repository.PF3FormRepository;
import com.reportplus.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PF3FormService {

    private final PF3FormRepository repository;
    private final CaseRepository caseRepository;
    private final UserRepository userRepository;


    public PF3FormService(
            PF3FormRepository repository,
            CaseRepository caseRepository,
            UserRepository userRepository
    ) {

        this.repository = repository;
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;

    }


    // =========================================
    // CREATE PF3 FORM
    // =========================================

    public PF3Form createForm(PF3Form form) {

        form.setStatus("PENDING");

        form.setCreatedAt(
                LocalDateTime.now()
        );

        return repository.save(form);

    }


    // =========================================
    // GET ALL
    // =========================================

    public List<PF3Form> getAll() {

        return repository.findAll();

    }


    // =========================================
    // GET BY ID
    // =========================================

    public PF3Form getById(Long id) {

        PF3Form form = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "PF3 Form not found"
                        )
                );

        return addHospitalInformation(form);

    }


    // =========================================
    // GET BY PF3 CODE
    // =========================================

    public PF3Form getByCode(String code) {

        PF3Form form = repository.findByPf3Code(code)
                .orElseThrow(() ->
                        new RuntimeException(
                                "PF3 Form not found"
                        )
                );

        return addHospitalInformation(form);

    }


    // =========================================
    // GET BY CITIZEN
    // =========================================

    public List<PF3Form> getByCitizen(
            Long citizenId
    ) {

        List<PF3Form> forms =
                repository.findByRequest_CitizenId(
                        citizenId
                );

        forms.forEach(
                this::addHospitalInformation
        );

        return forms;

    }


    // =========================================
    // GET BY HOSPITAL
    // =========================================

    public List<PF3Form> getByHospital(
            Long hospitalId
    ) {

        List<PF3Form> forms =
                repository.findByHospitalId(
                        hospitalId
                );

        forms.forEach(
                this::addHospitalInformation
        );

        return forms;

    }


    // =========================================
    // GET BY CODE + HOSPITAL
    // =========================================

    public PF3Form getByCodeForHospital(
            String code,
            Long hospitalId
    ) {

        PF3Form form =
                repository.findByPf3Code(code)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "PF3 Form not found"
                                )
                        );


        // =====================================
        // CHECK HOSPITAL OWNERSHIP
        // =====================================

        if (
                form.getHospitalId() == null ||
                !hospitalId.equals(
                        form.getHospitalId()
                )
        ) {

            throw new RuntimeException(
                    "This PF3 is not assigned to your hospital"
            );

        }


        // =====================================
        // ADD CASE + CITIZEN INFORMATION
        // =====================================

        return addHospitalInformation(form);

    }


    // =========================================
    // ADD HOSPITAL DISPLAY INFORMATION
    // =========================================

    private PF3Form addHospitalInformation(
            PF3Form form
    ) {

        if (form.getRequest() == null) {

            return form;

        }


        // =====================================
        // GET CITIZEN
        // =====================================

        Long citizenId =
                form.getRequest().getCitizenId();


        if (citizenId != null) {

            userRepository.findById(citizenId)
                    .ifPresent(user ->

                            form.setPatientName(
                                    user.getFullName()
                            )

                    );

        }


        // =====================================
        // GET CASE
        // =====================================

        Long caseId =
                form.getRequest().getCaseId();


        if (caseId != null) {

            caseRepository.findById(caseId)
                    .ifPresent(caseData -> {

                        form.setCaseNumber(
                                caseData.getCaseId()
                        );


                        form.setIncidentType(
                                caseData.getIncidentType()
                        );


                        String location =
                                caseData.getLocationName();


                        if (
                                location == null ||
                                location.trim().isEmpty()
                        ) {

                            location =
                                    caseData.getIncidentAddress();

                        }


                        form.setIncidentLocation(
                                location
                        );

                    });

        }


        return form;

    }

}