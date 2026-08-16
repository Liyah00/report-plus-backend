package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pf3_forms")
public class PF3Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;


    // =========================================
    // PF3 CODE
    // =========================================

    @Column(unique = true, nullable = false)
    private String pf3Code;


    // =========================================
    // REQUEST
    // =========================================

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private PF3Request request;


    // =========================================
    // HOSPITAL
    // =========================================

    private Long hospitalId;


    // =========================================
    // POLICE OFFICER
    // =========================================

    private Long approvedByPoliceId;


    // =========================================
    // STATUS
    // =========================================

    private String status;


    // =========================================
    // DATES
    // =========================================

    private LocalDateTime createdAt;

    private LocalDateTime completedAt;


    // =========================================
    // DISPLAY INFORMATION FOR HOSPITAL
    // These are NOT database columns.
    // =========================================

    @Transient
    private String patientName;

    @Transient
    private Long caseNumber;

    @Transient
    private String incidentType;

    @Transient
    private String incidentLocation;

}