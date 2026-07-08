package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pf3_requests")
public class PF3Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private Long citizenId;

    private String incidentType;

    private String description;

    private Double latitude;

    private Double longitude;

    private String locationName;

    private String incidentAddress;

    private String status; // PENDING, APPROVED, REJECTED

    private String pf3Code; // generated after approval
}