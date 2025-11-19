package org.impact.church.tenant.repository;

import org.impact.church.tenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findBySchemaName(String schemaName);
}
