package com.Hriitsh.societyvms.society_visitor_management.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class BulkResidentRequestDTO {

    @NotEmpty(message = "Resident list cannot be empty")
    @Valid
    private List<ResidentRequestDTO> residents;

    public BulkResidentRequestDTO() {
    }

    public List<ResidentRequestDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentRequestDTO> residents) {
        this.residents = residents;
    }
}
