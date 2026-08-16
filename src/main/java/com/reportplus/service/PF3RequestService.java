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


        // Generate PF3 code
        String code = "PF3-" + System.currentTimeMillis();

        request.setStatus("APPROVED");
        request.setPf3Code(code);
        request.setApprovedAt(LocalDateTime.now());


        PF3Request savedRequest =
                repository.save(request);


        // CHECK IF PF3 FORM ALREADY EXISTS
        PF3Form existingForm =
                pf3FormRepository
                        .findByRequest_RequestId(
                                savedRequest.getRequestId()
                        )
                        .orElse(null);


        // CREATE FORM ONLY ONCE
        if (existingForm == null) {


            /*
             * FIND ACTIVE HOSPITALS
             */
            List<Organization> hospitals =
                    organizationRepository
                            .findByOrganizationTypeAndStatus(
                                    "HOSPITAL",
                                    "ACTIVE"
                            );


            if (hospitals.isEmpty()) {

                throw new RuntimeException(
                        "No active hospital found"
                );

            }


            /*
             * CHECK PF3 LOCATION
             */
            if (savedRequest.getLatitude() == null ||
                    savedRequest.getLongitude() == null) {

                throw new RuntimeException(
                        "PF3 request location is missing"
                );

            }


            /*
             * FIND NEAREST HOSPITAL
             */

            Organization nearestHospital = null;

            double shortestDistance =
                    Double.MAX_VALUE;


            for (Organization hospital : hospitals) {


                // Skip hospital without coordinates
                if (hospital.getLatitude() == null ||
                        hospital.getLongitude() == null) {

                    continue;

                }


                double distance =
                        calculateDistance(
                                savedRequest.getLatitude(),
                                savedRequest.getLongitude(),
                                hospital.getLatitude(),
                                hospital.getLongitude()
                        );


                if (distance < shortestDistance) {

                    shortestDistance = distance;

                    nearestHospital = hospital;

                }

            }


            if (nearestHospital == null) {

                throw new RuntimeException(
                        "No active hospital with valid location found"
                );

            }


            /*
             * CREATE PF3 FORM
             */

            PF3Form form = new PF3Form();

            form.setPf3Code(code);

            form.setRequest(savedRequest);

            form.setHospitalId(
                    nearestHospital.getOrganizationId()
            );

            form.setStatus("PENDING");

            form.setCreatedAt(
                    LocalDateTime.now()
            );


            pf3FormRepository.save(form);


            System.out.println(
                    "PF3 assigned to hospital: "
                    + nearestHospital.getOrganizationName()
            );

        }


        return savedRequest;
    }


    /*
     * HAVERSINE DISTANCE
     *
     * Returns distance in kilometers
     */
    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {

        final int EARTH_RADIUS_KM = 6371;


        double latDistance =
                Math.toRadians(lat2 - lat1);

        double lonDistance =
                Math.toRadians(lon2 - lon1);


        double a =
                Math.sin(latDistance / 2)
                        * Math.sin(latDistance / 2)
                +
                Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);


        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );


        return EARTH_RADIUS_KM * c;
    }

}