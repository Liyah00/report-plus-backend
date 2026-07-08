package com.reportplus.controller;

import com.reportplus.model.Case;
import com.reportplus.service.CaseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
@CrossOrigin
public class CaseController {

    private final CaseService service;


    public CaseController(CaseService service) {
        this.service = service;
    }


    // CREATE CASE
    @PostMapping
    public Case createCase(@RequestBody Case caseEntity) {
        return service.createCase(caseEntity);
    }



    // GET ALL CASES
    @GetMapping
    public List<Case> getAllCases() {
        return service.getAllCases();
    }



    // GET CASE BY ID
    @GetMapping("/{id}")
    public Case getCaseById(@PathVariable Long id) {
        return service.getCaseById(id);
    }



    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public Case updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }



    // DELETE CASE
    @DeleteMapping("/{id}")
    public String deleteCase(@PathVariable Long id) {

        service.deleteCase(id);

        return "Case deleted successfully";
    }

}