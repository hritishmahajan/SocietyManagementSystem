package com.Hriitsh.societyvms.society_visitor_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hriitsh.societyvms.society_visitor_management.domain.VisitStatus;
import com.Hriitsh.societyvms.society_visitor_management.entity.Resident;
import com.Hriitsh.societyvms.society_visitor_management.entity.VisitRequest;
import com.Hriitsh.societyvms.society_visitor_management.repository.ResidentJdbcRepository;
import com.Hriitsh.societyvms.society_visitor_management.repository.VisitRequestRepository;

@Service
public class VisitRequestService {

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private ResidentJdbcRepository residentJdbcRepository;

    public VisitRequest createVisitRequest(Long residentId, VisitRequest request) {
        Resident resident = residentJdbcRepository.findById(residentId)
                .orElseThrow(() -> new RuntimeException("Resident not found with id: " + residentId));

        request.setResident(resident);
        request.setStatus(VisitStatus.APPROVED);
        return visitRequestRepository.save(request);
    }

    public List<VisitRequest> getAllVisitRequests() {
        return visitRequestRepository.findAll();
    }

    public VisitRequest getVisitRequestById(Long id) {
        return visitRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VisitRequest not found with id: " + id));
    }

    public VisitRequest checkIn(Long id) {
        VisitRequest request = getVisitRequestById(id);

        if (!request.getStatus().canTransitionTo(VisitStatus.CHECKED_IN)) {
            throw new IllegalStateException(
                    "Cannot check in a visit request with status: " + request.getStatus());
        }

        request.setStatus(VisitStatus.CHECKED_IN);
        request.setCheckedInAt(LocalDateTime.now());
        return visitRequestRepository.save(request);
    }

    public VisitRequest checkOut(Long id) {
        VisitRequest request = getVisitRequestById(id);

        if (!request.getStatus().canTransitionTo(VisitStatus.COMPLETED)) {
            throw new IllegalStateException(
                    "Cannot check out a visit request with status: " + request.getStatus());
        }

        request.setStatus(VisitStatus.COMPLETED);
        request.setCheckedOutAt(LocalDateTime.now());
        return visitRequestRepository.save(request);
    }

    public void deleteVisitRequest(Long id) {
        visitRequestRepository.deleteById(id);
    }
}
