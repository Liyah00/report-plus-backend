package com.reportplus.service;

import com.reportplus.model.Organization;
import com.reportplus.model.PF3Form;
import com.reportplus.model.PF3Request;
import com.reportplus.repository.OrganizationRepository;
import com.reportplus.repository.PF3FormRepository;
import com.reportplus.repository.PF3RequestRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PF3RequestService {

    private final PF3RequestRepository repository;
    private final PF3FormRepository pf3FormRepository;
    private final OrganizationRepository organizationRepository;


    public PF3RequestService(
            PF3RequestRepository repository,
            PF3FormRepository pf3FormRepository,
            OrganizationRepository organizationRepository
    ) {
        this.repository = repository;
        this.pf3FormRepository = pf3FormRepository;
        this.organizationRepository = organizationRepository;
    }


    // CREATE REQUEST
    public PF3Request createRequest(PF3Request req) {

        req.setStatus("PENDING");

        return repository.save(req);
    }


    // GET ALL REQUESTS
    public List<PF3Request> getAll() {

        return repository.findAll();
    }


    // GET BY ID
    public PF3Request getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));
    }


    // APPROVE REQUEST AND CREATE PF3 FORM
    public PF3Request approveRequest(Long requestId) {


        PF3Request request = repository.findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("PF3 Request not found"));


        request.setStatus("APPROVED");


        String code = "PF3-" + System.currentTimeMillis();

        request.setPf3Code(code);


        PF3Request savedRequest = repository.save(request);



        // CHECK IF FORM ALREADY EXISTS
        PF3Form existingForm =
                pf3FormRepository
                .findByRequest_RequestId(
                        savedRequest.getRequestId()
                )
                .orElse(null);



        // CREATE FORM ONLY ONCE
        if(existingForm == null){


            Organization hospital =
                    organizationRepository
                    .findByOrganizationTypeAndStatus(
                            "HOSPITAL",
                            "ACTIVE"
                    )
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "No active hospital found"
                            ));



            PF3Form form = new PF3Form();

            form.setPf3Code(code);

            form.setRequest(savedRequest);

            form.setHospitalId(
                    hospital.getOrganizationId()
            );

            form.setStatus("PENDING");

            form.setCreatedAt(
                    LocalDateTime.now()
            );


            pf3FormRepository.save(form);

        }


        return savedRequest;

    }

}