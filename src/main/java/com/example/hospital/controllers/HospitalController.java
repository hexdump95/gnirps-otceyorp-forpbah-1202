package com.example.hospital.controllers;

import com.example.hospital.Routes;
import com.example.hospital.entities.Hospital;
import com.example.hospital.exceptions.NotFoundException;
import com.example.hospital.services.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ABM Hospital")
@RequestMapping(path = Routes.HOSPITAL_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Operation(summary = "Listar Hospitales")
    @GetMapping
    public List<Hospital> findAllHospitales(
            @RequestParam(defaultValue = "false") boolean showDeleted
    ) {
        return hospitalService.findAll(showDeleted);
    }

    @Operation(summary = "Detalle Hospital")
    @GetMapping("/{id}")
    public ResponseEntity<Hospital> findOneHospital(@PathVariable Long id) throws NotFoundException {
        Hospital hospital = hospitalService.findById(id);
        if(hospital != null)
            return ResponseEntity.ok(hospital);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Crear Hospital")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping
    public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hospitalService.save(hospital));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Editar Hospital")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    public ResponseEntity<Hospital> putHospital(@PathVariable Long id, @RequestBody Hospital hospital) throws NotFoundException {
        Hospital entity = hospitalService.update(id, hospital);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @Operation(summary = "Borrar Hospital")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHospital(@PathVariable Long id) throws NotFoundException {
        Hospital entity = hospitalService.delete(id);
        if(entity != null)
            return ResponseEntity.ok(entity);
        else throw new NotFoundException(id);
    }
}
