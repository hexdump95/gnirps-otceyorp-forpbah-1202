package com.example.hospital.controllers;

import com.example.hospital.entities.Hospital;
import com.example.hospital.repositories.HospitalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM Hospital")
@RequestMapping(path = "/hospitales", produces = MediaType.APPLICATION_JSON_VALUE)
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Operation
    @GetMapping
    public List<Hospital> findAllHospitales() {
        return hospitalRepository.findAll();
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<Hospital> findOneHospital(@PathVariable Long id) {
        return hospitalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @PostMapping
    public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hospitalRepository.save(hospital));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<Hospital> putHospital(@PathVariable Long id, @RequestBody Hospital hospital){
        return hospitalRepository.findById(id)
                .map(h -> hospitalRepository.save(hospital))
                .map(ResponseEntity::ok)
                .orElseThrow(); // TODO
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHospital(@PathVariable Long id) {
        return hospitalRepository.findById(id)
                .map(h -> {
                    hospitalRepository.delete(h);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(); // TODO
    }
}
