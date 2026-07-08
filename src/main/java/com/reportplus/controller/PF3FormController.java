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

    // GET ALL FORMS
    @GetMapping
    public List<PF3Form> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PF3Form getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET BY PF3 CODE
    @GetMapping("/code/{code}")
    public PF3Form getByCode(@PathVariable String code) {
        return service.getByCode(code);
    }
}