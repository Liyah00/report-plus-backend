package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.model.Evidence;
import com.reportplus.repository.CaseRepository;
import com.reportplus.repository.EvidenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final CaseRepository caseRepository;

    public EvidenceService(EvidenceRepository evidenceRepository,
                           CaseRepository caseRepository) {
        this.evidenceRepository = evidenceRepository;
        this.caseRepository = caseRepository;
    }

    public Evidence createEvidence(Evidence evidence) {

        if (evidence.getCaseEntity() == null || evidence.getCaseEntity().getCaseId() == null) {
            throw new RuntimeException("caseId is required");
        }

        Long caseId = evidence.getCaseEntity().getCaseId();

        Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found: " + caseId));

        Evidence newEvidence = new Evidence();
        newEvidence.setCaseEntity(existingCase);
        newEvidence.setFileName(evidence.getFileName());
        newEvidence.setFileType(evidence.getFileType());
        newEvidence.setFilePath(evidence.getFilePath());

        return evidenceRepository.save(newEvidence);
    }

    public List<Evidence> getAllEvidence() {
        return evidenceRepository.findAll();
    }

    public Evidence getEvidenceById(Long id) {
        return evidenceRepository.findById(id).orElse(null);
    }
}