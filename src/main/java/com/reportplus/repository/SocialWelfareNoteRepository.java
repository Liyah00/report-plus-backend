package com.reportplus.repository;

import com.reportplus.model.SocialWelfareNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialWelfareNoteRepository extends JpaRepository<SocialWelfareNote, Long> {

    List<SocialWelfareNote> findByCaseEntityCaseId(Long caseId);

}