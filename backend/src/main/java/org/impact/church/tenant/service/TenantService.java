package org.impact.church.tenant.service;

import lombok.RequiredArgsConstructor;
import org.impact.church.security.service.SecurityAdminService;
import org.impact.church.tenant.model.Tenant;
import org.impact.church.tenant.repository.TenantRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenants;
    private final JdbcTemplate jdbc;
    private final SecurityAdminService securityAdminService;

    @Transactional
    public Tenant createChurchWithAdmin(String churchName,
                                        String adminEmail,
                                        String adminPassword,
                                        String adminFullName) throws Exception {

        // 1 — Création du tenant
        String schemaName = "church_" + churchName.toLowerCase().replace(" ", "_");

        Tenant tenant = Tenant.builder()
                .name(churchName)
                .schemaName(schemaName)
                .build();
        tenant = tenants.save(tenant);

        // 2 — Création du schéma
        jdbc.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);

        // 3 — Initialisation des tables de l’église
        String sql = new String(
                new ClassPathResource("sql/init_church_schema.sql").getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        // On injecte le nom du schéma au début du script
        sql = "SET search_path TO " + schemaName + ";\n" + sql;
        jdbc.execute(sql);

        // 4 — Création du Church Admin
        securityAdminService.createChurchAdmin(
                adminEmail,
                adminPassword,
                adminFullName,
                tenant.getId()
        );

        return tenant;
    }
}
