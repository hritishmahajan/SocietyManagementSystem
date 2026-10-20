package com.Hriitsh.societyvms.society_visitor_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Hriitsh.societyvms.society_visitor_management.entity.VisitRequest;
import com.Hriitsh.societyvms.society_visitor_management.service.VisitRequestService;

@RestController
@RequestMapping("/api/visit-requests")
public class VisitRequestController {

    @Autowired
    private VisitRequestService visitRequestService;

    @PostMapping("/resident/{residentId}")
    public VisitRequest createVisitRequest(@PathVariable Long residentId, @RequestBody VisitRequest request) {
        return visitRequestService.createVisitRequest(residentId, request);
    }

    @GetMapping
    public List<VisitRequest> getAllVisitRequests() {
        return visitRequestService.getAllVisitRequests();
    }

    @GetMapping("/{id}")
    public VisitRequest getVisitRequestById(@PathVariable Long id) {
        return visitRequestService.getVisitRequestById(id);
    }

    @PutMapping("/{id}/check-in")
    public VisitRequest checkIn(@PathVariable Long id) {
        return visitRequestService.checkIn(id);
    }

    @PutMapping("/{id}/check-out")
    public VisitRequest checkOut(@PathVariable Long id) {
        return visitRequestService.checkOut(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVisitRequest(@PathVariable Long id) {
        visitRequestService.deleteVisitRequest(id);
    }
}
