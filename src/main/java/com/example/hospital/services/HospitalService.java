package com.example.hospital.services;

import com.example.hospital.entities.Hospital;

import java.util.List;

public interface HospitalService {
    List<Hospital> findAll(boolean showDeleted);
    Hospital findById(Long id);
    Hospital save(Hospital hospital);
    Hospital update(Long id, Hospital hospital);
    Hospital delete(Long id);
}
