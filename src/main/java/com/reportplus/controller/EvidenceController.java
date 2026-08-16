package com.reportplus.controller;

import com.reportplus.model.Evidence;
import com.reportplus.service.EvidenceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/evidence")
@CrossOrigin(origins = "http://localhost:5173")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Evidence uploadEvidence(

            @RequestParam Long caseId,

            @RequestParam MultipartFile file

    ) throws IOException {

        return evidenceService.uploadEvidence(caseId, file);

    }

    @GetMapping
    public List<Evidence> getAllEvidence() {

        return evidenceService.getAllEvidence();

    }

    @GetMapping("/{id}")
    public Evidence getEvidenceById(@PathVariable Long id) {

        return evidenceService.getEvidenceById(id);

    }

    @GetMapping("/case/{caseId}")
public List<Evidence> getEvidenceByCase(
        @PathVariable Long caseId
) {

    return evidenceService.getEvidenceByCase(caseId);

}

}