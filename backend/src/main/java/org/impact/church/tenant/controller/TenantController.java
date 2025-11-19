package org.impact.church.tenant.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.impact.church.tenant.service.TenantService;
import org.impact.church.tenant.model.Tenant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Tenant create(@RequestBody CreateChurchRequest req) throws Exception {
        return tenantService.createChurchWithAdmin(
                req.getChurchName(),
                req.getAdminEmail(),
                req.getAdminPassword(),
                req.getAdminFullName()
        );
    }

    @Data
    public static class CreateChurchRequest {
        private String churchName;
        private String adminEmail;
        private String adminPassword;
        private String adminFullName;
    }
}
