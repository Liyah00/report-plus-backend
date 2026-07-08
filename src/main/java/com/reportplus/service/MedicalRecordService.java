package com.reportplus.service;

import com.reportplus.model.MedicalRecord;
import com.reportplus.model.PF3Form;
import com.reportplus.repository.MedicalRecordRepository;
import com.reportplus.repository.PF3FormRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository repository;
    private final PF3FormRepository pf3FormRepository;


    public MedicalRecordService(
            MedicalRecordRepository repository,
            PF3FormRepository pf3FormRepository
    ) {
        this.repository = repository;
        this.pf3FormRepository = pf3FormRepository;
    }



    // CREATE MEDICAL RECORD BY HOSPITAL
    public MedicalRecord createRecord(
            Long pf3Id,
            MedicalRecord record
    ) {


        PF3Form pf3 = pf3FormRepository.findById(pf3Id)
                .orElseThrow(() ->
                        new RuntimeException("PF3 Form not found")
                );


        record.setPf3(pf3);


        MedicalRecord savedRecord =
                repository.save(record);



        // update PF3 status after hospital completion
        pf3.setStatus("COMPLETED");
        pf3.setCompletedAt(LocalDateTime.now());

        pf3FormRepository.save(pf3);



        return savedRecord;

    }



    // GET ALL MEDICAL RECORDS
    public List<MedicalRecord> getAll(){

        return repository.findAll();

    }



    // GET BY ID
    public MedicalRecord getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medical record not found")
                );

    }

}