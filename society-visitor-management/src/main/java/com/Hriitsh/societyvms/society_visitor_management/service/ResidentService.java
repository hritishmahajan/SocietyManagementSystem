package com.Hriitsh.societyvms.society_visitor_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Hriitsh.societyvms.society_visitor_management.dto.BulkResidentRequestDTO;
import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentRequestDTO;
import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentResponseDTO;
import com.Hriitsh.societyvms.society_visitor_management.entity.Resident;
import com.Hriitsh.societyvms.society_visitor_management.repository.ResidentJdbcRepository;

@Service
public class ResidentService {

    @Autowired
    private ResidentJdbcRepository residentJdbcRepository;

    @CacheEvict (value = "residents", allEntries = true)
    public ResidentResponseDTO createResident(ResidentRequestDTO request) {
        Resident resident = new Resident(
                request.getName(),
                request.getFlatNumber(),
                request.getPhoneNumber(),
                request.getEmail()
        );
        Resident saved = residentJdbcRepository.save(resident);
        return toResponseDTO(saved);
    }

    @CacheEvict (value = "residents", allEntries = true)
    @Transactional
    public List<ResidentResponseDTO> createResidentsBulk(BulkResidentRequestDTO bulkRequest) {
        List<ResidentResponseDTO> savedResidents = new ArrayList<>();

        for (ResidentRequestDTO request : bulkRequest.getResidents()) {
            if (residentJdbcRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Duplicate email found, rolling back entire batch: " + request.getEmail());
            }

            Resident resident = new Resident(
                    request.getName(),
                    request.getFlatNumber(),
                    request.getPhoneNumber(),
                    request.getEmail()
            );
            Resident saved = residentJdbcRepository.save(resident);
            savedResidents.add(toResponseDTO(saved));
        }

        return savedResidents;
    }

    @Cacheable(value = "residents", key = "'all'")
    public List<ResidentResponseDTO> getAllResidents() {
        return residentJdbcRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Cacheable (value = "residents", key = "#id")
    public ResidentResponseDTO getResidentById(Long id) {
        Resident resident = findResidentOrThrow(id);
        return toResponseDTO(resident);
    }

    @CacheEvict (value = "residents", allEntries = true)
    public ResidentResponseDTO updateResident(Long id, ResidentRequestDTO request) {
        Resident existing = findResidentOrThrow(id);
        existing.setName(request.getName());
        existing.setFlatNumber(request.getFlatNumber());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setEmail(request.getEmail());
        Resident updated = residentJdbcRepository.update(existing);
        return toResponseDTO(updated);
    }

    @CacheEvict (value = "residents", allEntries = true)
    public void deleteResident(Long id) {
        residentJdbcRepository.deleteById(id);
    }

    private Resident findResidentOrThrow(Long id) {
        return residentJdbcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found with id: " + id));
    }

    private ResidentResponseDTO toResponseDTO(Resident resident) {
        return new ResidentResponseDTO(
                resident.getId(),
                resident.getName(),
                resident.getFlatNumber(),
                resident.getPhoneNumber(),
                resident.getEmail()
        );
    }
}
