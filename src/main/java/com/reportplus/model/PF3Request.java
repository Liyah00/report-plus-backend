package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pf3_requests")
public class PF3Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "citizen_id", nullable = false)
    private Long citizenId;

    @Column(name = "case_id", nullable = false)
    private Long caseId;

    @Column(nullable = false)
    private String status = "PENDING";

    @Column(name = "pf3_code")
    private String pf3Code;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @PrePersist
    public void onCreate() {
        requestedAt = LocalDateTime.now();
    }

@Column(name = "latitude")
private Double latitude;

@Column(name = "longitude")
private Double longitude;

@Column(name = "location_name")
private String locationName;

}