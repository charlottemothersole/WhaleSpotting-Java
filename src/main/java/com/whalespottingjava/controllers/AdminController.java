package com.whalespottingjava.controllers;

import com.whalespottingjava.models.requests.AdminApprovalRequests;

import com.whalespottingjava.services.SightingService;
import com.whalespottingjava.models.enums.ApprovalStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final SightingService sightingService;

    @Autowired
    public AdminController (SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @ModelAttribute("status")
    public ApprovalStatus[] status() {
        return ApprovalStatus.values();
    }


    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public String getAdminPage(Model model) {

        AdminApprovalRequests form = new AdminApprovalRequests();

        model.addAttribute("form", form);

        model.addAttribute("sightings", sightingService.getAllPendingSightings());

        return "admin";
    }


    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public String postAdminApprovalRequest(@ModelAttribute AdminApprovalRequests form, Model model) {
        System.out.println(form.getAdminApprovalRequests());
        
        model.addAttribute("form", form);

        model.addAttribute("sightings", sightingService.getAllPendingSightings());
        
        return "admin";
    }
}
