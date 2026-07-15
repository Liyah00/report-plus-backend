package com.reportplus.controller;

import com.reportplus.model.SocialWelfareNote;
import com.reportplus.service.SocialWelfareNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social-welfare-notes")
@CrossOrigin
public class SocialWelfareNoteController {

    private final SocialWelfareNoteService service;

    public SocialWelfareNoteController(SocialWelfareNoteService service) {
        this.service = service;
    }

    // CREATE NOTE
    @PostMapping
    public SocialWelfareNote createNote(@RequestBody SocialWelfareNote note) {
        return service.createNote(note);
    }

    // GET ALL NOTES
    @GetMapping
    public List<SocialWelfareNote> getAll() {
        return service.getAll();
    }

    // GET NOTE BY ID
    @GetMapping("/{id}")
    public SocialWelfareNote getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET NOTES BY CASE
    @GetMapping("/case/{caseId}")
    public List<SocialWelfareNote> getByCase(@PathVariable Long caseId) {
        return service.getByCase(caseId);
    }
}