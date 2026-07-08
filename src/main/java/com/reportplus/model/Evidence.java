package com.reportplus.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evidence_id")
    private Long evidenceId;


    @ManyToOne
    @JoinColumn(name = "case_id")
    @EqualsAndHashCode.Exclude
    private Case caseEntity;


    @Column(name = "file_name")
    private String fileName;


    @Column(name = "file_type")
    private String fileType;


    @Column(name = "file_path")
    private String filePath;
}