package com.reportplus.service;

import com.reportplus.model.Case;
import com.reportplus.model.SocialWelfareNote;
import com.reportplus.repository.CaseRepository;
import com.reportplus.repository.SocialWelfareNoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SocialWelfareNoteService {

    private final SocialWelfareNoteRepository repository;
    private final CaseRepository caseRepository;

    public SocialWelfareNoteService(
            SocialWelfareNoteRepository repository,
            CaseRepository caseRepository) {

        this.repository = repository;
        this.caseRepository = caseRepository;
    }

    // Create Note
    public SocialWelfareNote createNote(SocialWelfareNote note) {

        if (note.getCaseEntity() == null ||
                note.getCaseEntity().getCaseId() == null) {
            throw new RuntimeException("Case is required");
        }

        Case existingCase = caseRepository.findById(
                note.getCaseEntity().getCaseId())
                .orElseThrow(() ->
                        new RuntimeException("Case not found"));

        note.setCaseEntity(existingCase);
        note.setCreatedAt(LocalDateTime.now());

        return repository.save(note);
    }

    // Get All Notes
    public List<SocialWelfareNote> getAll() {
        return repository.findAll();
    }

    // Get Note By ID
    public SocialWelfareNote getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Note not found"));
    }

    // Get Notes By Case
    public List<SocialWelfareNote> getByCase(Long caseId) {
        return repository.findByCaseEntityCaseId(caseId);
    }
}