package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.model.Evidence;
import com.reportplus.repository.CaseRepository;
import com.reportplus.repository.EvidenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final CaseRepository caseRepository;

    private final String uploadDir = "uploads/evidence/";

    public EvidenceService(
            EvidenceRepository evidenceRepository,
            CaseRepository caseRepository
    ) {
        this.evidenceRepository = evidenceRepository;
        this.caseRepository = caseRepository;
    }

    // ===========================
    // UPLOAD EVIDENCE
    // ===========================

    public Evidence uploadEvidence(Long caseId, MultipartFile file)
            throws IOException {

        Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));

        Files.createDirectories(Paths.get(uploadDir));

        String fileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path filePath =
                Paths.get(uploadDir, fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        Evidence evidence = new Evidence();

        evidence.setCaseEntity(existingCase);

        evidence.setFileName(fileName);

        evidence.setFileType(file.getContentType());

        evidence.setFilePath(filePath.toString());

        return evidenceRepository.save(evidence);
    }

    // ===========================
    // GET ALL
    // ===========================

    public List<Evidence> getAllEvidence() {

        return evidenceRepository.findAll();

    }

    // ===========================
    // GET BY ID
    // ===========================

    public Evidence getEvidenceById(Long id) {

        return evidenceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Evidence not found"));

    }

    public List<Evidence> getEvidenceByCase(Long caseId) {

    return evidenceRepository.findByCaseEntity_CaseId(caseId);

}

}