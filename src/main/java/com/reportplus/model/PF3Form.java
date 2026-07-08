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

    // PF3 Code generated after approval
    @Column(unique = true, nullable = false)
    private String pf3Code;

    // Request this form belongs to
    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private PF3Request request;

    // Hospital assigned to examine the victim
    private Long hospitalId;

    // Police officer who approved
    private Long approvedByPoliceId;

    // Current form status
    private String status;

    // Date created
    private LocalDateTime createdAt;

    // Date hospital completed examination
    private LocalDateTime completedAt;
}