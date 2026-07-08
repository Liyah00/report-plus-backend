package com.reportplus.controller;

import com.reportplus.model.Evidence;
import com.reportplus.service.EvidenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evidence")
@CrossOrigin
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping
    public Evidence createEvidence(@RequestBody Evidence evidence) {
        return evidenceService.createEvidence(evidence);
    }

    @GetMapping
    public List<Evidence> getAllEvidence() {
        return evidenceService.getAllEvidence();
    }

    @GetMapping("/{id}")
    public Evidence getEvidenceById(@PathVariable Long id) {
        return evidenceService.getEvidenceById(id);
    }
}