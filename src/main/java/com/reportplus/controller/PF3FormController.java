package com.reportplus.controller;

import com.reportplus.model.PF3Form;
import com.reportplus.service.PF3FormService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pf3-forms")
@CrossOrigin
public class PF3FormController {

    private final PF3FormService service;

    public PF3FormController(PF3FormService service) {
        this.service = service;
    }


    // =========================================
    // GET ALL PF3 FORMS
    // =========================================

    @GetMapping
    public List<PF3Form> getAll() {

        return service.getAll();

    }


    // =========================================
    // GET PF3 FORM BY ID
    // =========================================

    @GetMapping("/{id}")
    public PF3Form getById(
            @PathVariable Long id
    ) {

        return service.getById(id);

    }


    // =========================================
    // GET PF3 FORM BY PF3 CODE
    // =========================================

    @GetMapping("/code/{code}")
    public PF3Form getByCode(
            @PathVariable String code
    ) {

        return service.getByCode(code);

    }


    // =========================================
    // GET PF3 FORMS BY CITIZEN
    // =========================================

    @GetMapping("/citizen/{citizenId}")
    public List<PF3Form> getByCitizen(
            @PathVariable Long citizenId
    ) {

        return service.getByCitizen(citizenId);

    }


    // =========================================
    // GET PF3 FORMS BY HOSPITAL
    // =========================================

    @GetMapping("/hospital/{hospitalId}")
    public List<PF3Form> getByHospital(
            @PathVariable Long hospitalId
    ) {

        return service.getByHospital(hospitalId);

    }


    // =========================================
    // OPEN PF3 BY CODE FOR SPECIFIC HOSPITAL
    // =========================================

    @GetMapping("/code/{code}/hospital/{hospitalId}")
    public PF3Form getByCodeForHospital(
            @PathVariable String code,
            @PathVariable Long hospitalId
    ) {

        return service.getByCodeForHospital(
                code,
                hospitalId
        );

    }

}