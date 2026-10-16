package com.Hriitsh.societyvms.society_visitor_management.entity;

import java.time.LocalDateTime;

import com.Hriitsh.societyvms.society_visitor_management.domain.VisitStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "visit_requests")
public class VisitRequest {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public LocalDateTime getCheckedInAt() {
        return checkedInAt;
    }

    public void setCheckedInAt(LocalDateTime checkedInAt) {
        this.checkedInAt = checkedInAt;
    }

    public LocalDateTime getCheckedOutAt() {
        return checkedOutAt;
    }

    public void setCheckedOutAt(LocalDateTime checkedOutAt) {
        this.checkedOutAt = checkedOutAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    private String visitorName;
    private String visitorPhone;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private LocalDateTime checkedInAt;
    private LocalDateTime checkedOutAt;

    public VisitRequest() {
    }

    public VisitRequest(Resident resident, String visitorName, String visitorPhone,
                         LocalDateTime validFrom, LocalDateTime validUntil) {
        this.resident = resident;
        this.visitorName = visitorName;
        this.visitorPhone = visitorPhone;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.status = VisitStatus.PENDING;
    }
}