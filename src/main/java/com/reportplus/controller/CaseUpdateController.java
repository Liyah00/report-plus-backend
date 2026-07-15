package com.reportplus.controller;

import com.reportplus.model.CaseUpdate;
import com.reportplus.service.CaseUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/case-updates")
@CrossOrigin
public class CaseUpdateController {

    private final CaseUpdateService service;

    public CaseUpdateController(CaseUpdateService service) {
        this.service = service;
    }

    // Create Update
    @PostMapping
    public CaseUpdate createUpdate(@RequestBody CaseUpdate update) {
        return service.createUpdate(update);
    }

    // Get All Updates
    @GetMapping
    public List<CaseUpdate> getAll() {
        return service.getAll();
    }

    // Get Update By ID
    @GetMapping("/{id}")
    public CaseUpdate getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Get Updates By Case
    @GetMapping("/case/{caseId}")
    public List<CaseUpdate> getByCase(@PathVariable Long caseId) {
        return service.getByCase(caseId);
    }
}