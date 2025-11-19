package org.impact.church.security.service;

import lombok.RequiredArgsConstructor;
import org.impact.church.security.model.Role;
import org.impact.church.security.model.UserAccount;
import org.impact.church.security.repository.RoleRepository;
import org.impact.church.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SecurityAdminService {

    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder encoder;

    @Transactional
    public Role ensureRole(String name, String description) {
        return roles.findByName(name).orElseGet(() -> roles.save(Role.builder().name(name).description(description).build()));
    }

    @Transactional
    public UserAccount createSuperAdmin(String email, String rawPassword, String fullName) {
        var role = ensureRole("SUPER_ADMIN", "Super administrator");
        UserAccount u = UserAccount.builder()
                .email(email)
                .password(encoder.encode(rawPassword))
                .fullName(fullName)
                .enabled(true)
                .build();
        u.getRoles().add(role);
        return users.save(u);
    }

    @Transactional
    public UserAccount createChurchAdmin(String email, String rawPassword, String fullName, Long tenantId) {
        var role = ensureRole("CHURCH_ADMIN", "Local church admin");
        UserAccount u = UserAccount.builder()
                .email(email)
                .password(encoder.encode(rawPassword))
                .fullName(fullName)
                .tenantId(tenantId)
                .enabled(true)
                .build();
        u.getRoles().add(role);
        return users.save(u);
    }

    // helpers for leader roles
    @Transactional
    public Role ensureDepartmentLeaderRole() {
        return ensureRole("DEPARTMENT_LEADER", "Leader of a department");
    }

    @Transactional
    public Role ensureTrainingLeaderRole() {
        return ensureRole("TRAINING_LEADER", "Leader of trainings");
    }
}
