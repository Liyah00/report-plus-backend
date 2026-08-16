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


    // =========================================
    // PF3
    // =========================================

    @OneToOne
    @JoinColumn(name = "pf3_id", nullable = false)
    private PF3Form pf3;


    // =========================================
    // HOSPITAL STAFF
    // =========================================

    @Column(name = "hospital_staff_id")
    private Long hospitalStaffId;


    // =========================================
    // MEDICAL EXAMINATION
    // =========================================

    @Column(columnDefinition = "TEXT")
    private String injuries;


    @Column(columnDefinition = "TEXT")
    private String findings;


    @Column(columnDefinition = "TEXT")
    private String treatment;


    @Column(name = "doctor_opinion", columnDefinition = "TEXT")
    private String doctorOpinion;


    @Column(columnDefinition = "TEXT")
    private String recommendation;


    @Column(name = "doctor_name")
    private String doctorName;

}