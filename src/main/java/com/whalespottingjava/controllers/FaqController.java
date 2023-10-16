package com.whalespottingjava.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.whalespottingjava.models.MemberDetails;

@Controller
public class FaqController {
    @GetMapping("/faq")
    public String getFaqPage(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Boolean isLoggedIn = authentication.isAuthenticated();
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "faq";
    }
}
