package com.reportplus.controller;

import com.reportplus.model.PF3Request;
import com.reportplus.service.PF3RequestService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pf3-requests")
@CrossOrigin
public class PF3RequestController {

    private final PF3RequestService service;

    public PF3RequestController(PF3RequestService service) {
        this.service = service;
    }

    // CREATE PF3 REQUEST
@PostMapping
public PF3Request create(@Valid @RequestBody PF3Request req) {

    System.out.println(">>> ENTERED CONTROLLER <<<");

    return service.createRequest(req);
}

    // GET ALL
    @GetMapping
    public List<PF3Request> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PF3Request getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/approve")
public PF3Request approve(@PathVariable Long id) {
    return service.approveRequest(id);
}


}


