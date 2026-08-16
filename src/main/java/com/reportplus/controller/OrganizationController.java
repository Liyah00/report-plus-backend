package com.reportplus.controller;

import com.reportplus.model.Organization;
import com.reportplus.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin
public class OrganizationController {

    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }


    @PostMapping
    public Organization create(@RequestBody Organization organization) {
        return service.create(organization);
    }


    @GetMapping
    public List<Organization> getAll() {
        return service.getAll();
    }


    @GetMapping("/{id}")
    public Organization getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @GetMapping("/hospitals")
    public List<Organization> getHospitals() {
        return service.getActiveHospitals();
    }

// UPDATE
@PutMapping("/{id}")
public Organization update(
        @PathVariable Long id,
        @RequestBody Organization organization
) {

    return service.update(id, organization);

}


// DELETE
@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {

    service.delete(id);

}

}