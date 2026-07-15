package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "case_updates")
public class CaseUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "update_id")
    private Long updateId;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    private Case caseEntity;

    @Column(name = "police_officer_id")
    private Long policeOfficerId;

    @Column(columnDefinition = "TEXT")
    private String updateNote;

    @Column(name = "case_status")
    private String caseStatus;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}