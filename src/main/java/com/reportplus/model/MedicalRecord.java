package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;


    @OneToOne
    @JoinColumn(name = "pf3_id", nullable = false)
    private PF3Form pf3;


    @Column(name = "hospital_staff_id")
    private Long hospitalStaffId;


    private String injuries;

    private String findings;

    private String treatment;

}