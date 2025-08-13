package com.example.hospital.services;
import com.example.hospital.entities.District;

import java.util.List;

public interface LocalidadService {
    List<District> findAll(boolean showDeleted);
    District findById(Long id);
    District save(District district);
    District update(Long id, District district);
    District delete(Long id);
}
