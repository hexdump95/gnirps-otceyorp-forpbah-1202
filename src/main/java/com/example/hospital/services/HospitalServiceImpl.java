package com.example.hospital.services;

import com.example.hospital.entities.Hospital;
import com.example.hospital.repositories.HospitalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> findAll(boolean showDeleted) {
        if(showDeleted)
            return hospitalRepository.findAll();
        else return hospitalRepository.findByFechaBajaHospitalIsNull();
    }

    @Override
    public Hospital findById(Long id) {
        return hospitalRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository
                .save(hospital);
    }

    @Override
    public Hospital update(Long id, Hospital hospital) {
        return hospitalRepository.findById(id)
                .map(h -> hospitalRepository.save(hospital))
                .orElse(null);
    }

    @Override
    public Hospital delete(Long id) {
        return hospitalRepository.findById(id)
                .map(h -> {
                    h.setFechaBajaHospital(LocalDateTime.now());
                    return hospitalRepository.save(h);
                }).orElse(null);
    }


}

