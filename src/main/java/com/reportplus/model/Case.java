package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private Long caseId;


    @Column(name = "citizen_id")
    private Long citizenId;


    @Column(name = "assigned_police_org_id")
    private Long assignedPoliceOrgId;


    private String incidentType;


    @Column(columnDefinition = "TEXT")
    private String description;


    private Double latitude;

    private Double longitude;


    private String locationName;


    private String incidentAddress;


    private String caseStatus;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}