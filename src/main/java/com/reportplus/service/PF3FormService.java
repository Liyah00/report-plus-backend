package com.reportplus.service;
import com.reportplus.model.PF3Form;
import com.reportplus.repository.PF3FormRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PF3FormService {

    private final PF3FormRepository repository;

    public PF3FormService(PF3FormRepository repository) {
        this.repository = repository;
    }

    // CREATE FORM (after approval)
    public PF3Form createForm(PF3Form form) {
        form.setStatus("PENDING");
        form.setCreatedAt(LocalDateTime.now());
        return repository.save(form);
    }

    public List<PF3Form> getAll() {
        return repository.findAll();
    }

    public PF3Form getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PF3 Form not found"));
    }

    public PF3Form getByCode(String code) {
        return repository.findByPf3Code(code)
                .orElseThrow(() -> new RuntimeException("PF3 Form not found"));
    }
}