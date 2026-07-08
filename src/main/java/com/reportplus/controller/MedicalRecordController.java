package com.reportplus.controller;

import com.reportplus.model.MedicalRecord;
import com.reportplus.service.MedicalRecordService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin
public class MedicalRecordController {


    private final MedicalRecordService service;


    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }



    // CREATE MEDICAL RECORD
    @PostMapping("/pf3/{pf3Id}")
    public MedicalRecord createRecord(
            @PathVariable Long pf3Id,
            @RequestBody MedicalRecord record
    ){

        return service.createRecord(pf3Id, record);

    }



    // GET ALL RECORDS
    @GetMapping
    public List<MedicalRecord> getAll(){

        return service.getAll();

    }



    // GET RECORD BY ID
    @GetMapping("/{id}")
    public MedicalRecord getById(
            @PathVariable Long id
    ){

        return service.getById(id);

    }

}