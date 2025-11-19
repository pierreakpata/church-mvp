package org.impact.church.security.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.impact.church.security.jwt.JwtUtil;
import org.impact.church.security.repository.UserRepository;
import org.impact.church.tenant.repository.TenantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository  userRepository;
    private final TenantRepository tenantRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());

        Authentication auth = authenticationManager.authenticate(token);

        // role extraction (first authority)
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("USER");

        //Je récupère le schema associé à l'utilisateur
        Long idTenant = userRepository.findByEmail(req.getUsername()).get().getTenantId();
        String tenantUser = tenantRepository.findById(idTenant).get().getSchemaName();

        String tenant = tenantUser == null ? "public" : tenantUser;

        String jwt = jwtUtil.generateToken(req.getUsername(), role, tenant);

        return ResponseEntity.ok(new LoginResponse(jwt, "Bearer", tenant));
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class LoginResponse {
        private final String accessToken;
        private final String tokenType;
        private final String tenant;
    }
}
