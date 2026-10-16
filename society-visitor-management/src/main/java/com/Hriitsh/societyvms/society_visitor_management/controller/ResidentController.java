package com.Hriitsh.societyvms.society_visitor_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Hriitsh.societyvms.society_visitor_management.dto.BulkResidentRequestDTO;
import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentRequestDTO;
import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentResponseDTO;
import com.Hriitsh.societyvms.society_visitor_management.service.ResidentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping
    public ResidentResponseDTO createResident(@Valid @RequestBody ResidentRequestDTO request) {
        return residentService.createResident(request);
    }

    @PostMapping("/bulk")
    public List<ResidentResponseDTO> createResidentsBulk(@Valid @RequestBody BulkResidentRequestDTO bulkRequest) {
        return residentService.createResidentsBulk(bulkRequest);
    }

    @GetMapping
    public List<ResidentResponseDTO> getAllResidents() {
        return residentService.getAllResidents();
    }

    @GetMapping("/{id}")
    public ResidentResponseDTO getResidentById(@PathVariable Long id) {
        return residentService.getResidentById(id);
    }

    @PutMapping("/{id}")
    public ResidentResponseDTO updateResident(@PathVariable Long id, @Valid @RequestBody ResidentRequestDTO request) {
        return residentService.updateResident(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);
    }
}
