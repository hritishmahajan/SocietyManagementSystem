package com.Hriitsh.societyvms.society_visitor_management.dto;
import java.io.Serializable;

public class ResidentResponseDTO implements Serializable {

    private Long id;
    private String name;
    private String flatNumber;
    private String phoneNumber;
    private String email;

    public ResidentResponseDTO() {
    }

    public ResidentResponseDTO(Long id, String name, String flatNumber, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.flatNumber = flatNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
