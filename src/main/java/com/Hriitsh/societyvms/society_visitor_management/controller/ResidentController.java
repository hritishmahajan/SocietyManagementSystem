package com.Hriitsh.societyvms.society_visitor_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Hriitsh.societyvms.society_visitor_management.dto.BulkResidentRequestDTO;
import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentRequestDTO;
import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentResponseDTO;
import com.Hriitsh.societyvms.society_visitor_management.service.ExcelService;
import com.Hriitsh.societyvms.society_visitor_management.service.NotificationService;
import com.Hriitsh.societyvms.society_visitor_management.service.ResidentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;
    
    @Autowired 
    private NotificationService notificationService;

    @Autowired 
    private ExcelService excelService;

    @PostMapping
    public ResidentResponseDTO createResident(@Valid @RequestBody ResidentRequestDTO request) {
        return residentService.createResident(request);
    }

    @PostMapping("/bulk")
    public List<ResidentResponseDTO> createResidentsBulk(@Valid @RequestBody BulkResidentRequestDTO bulkRequest) {
        return residentService.createResidentsBulk(bulkRequest);
    }

    @PostMapping("/upload-excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception{

    List<ResidentRequestDTO> residents = excelService.parseExcelFile(file);

    BulkResidentRequestDTO bulkRequest = new BulkResidentRequestDTO();
    bulkRequest.setResidents(residents);

    residentService.createResidentsBulk(bulkRequest);
    return ResponseEntity.ok("Successfully uploaded " + residents.size() + " residents from Excel");
    }
    

    @GetMapping
    public List<ResidentResponseDTO> getAllResidents() {
        return residentService.getAllResidents();
    }

    @GetMapping("/{id}")
    public ResidentResponseDTO getResidentById(@PathVariable Long id) {
        return residentService.getResidentById(id);
    }

    @GetMapping ("/{id}/verify")
    public String verifyResident(@PathVariable Long id){
        ResidentResponseDTO resident = residentService.getResidentById(id);
        return notificationService.verifyResidentDetails(resident.getEmail(), resident.getPhoneNumber());
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
