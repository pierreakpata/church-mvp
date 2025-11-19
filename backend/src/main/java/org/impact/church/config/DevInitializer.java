package org.impact.church.config;

import lombok.RequiredArgsConstructor;
import org.impact.church.security.service.SecurityAdminService;
import org.impact.church.config.multitenant.TenantContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // ne tourne que si spring.profiles.active=dev
@RequiredArgsConstructor
public class DevInitializer implements CommandLineRunner {

    private final SecurityAdminService securityAdminService;

    @Value("${app.init-superadmin:true}")
    private boolean initSuperAdmin;

    @Override
    public void run(String... args) {
        /*if (!initSuperAdmin) return;
        TenantContext.setCurrentTenant("public");

        System.out.println("=== Initialisation DEV : Rôles + Super Admin ===");

        // Création des rôles principaux
        securityAdminService.ensureRole("SUPER_ADMIN", "Global system admin");
        securityAdminService.ensureRole("CHURCH_ADMIN", "Local church admin");
        securityAdminService.ensureRole("DEPARTMENT_LEADER", "Department leader");
        securityAdminService.ensureRole("TRAINING_LEADER", "Training leader");

        // Création d’un super admin DEV
        securityAdminService.createSuperAdmin(
                "super@church.app",
                "super123",
                "Development Super Admin"
        );

        System.out.println("=== Super Admin créé : super@church.app / super123 ===");

        TenantContext.clear();*/
    }
}
