package com.jtbank.backend.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.Optional;

@Component
public class AuditingImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(currentUser);
    }
}
