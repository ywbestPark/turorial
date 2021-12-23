package com.example.tutorial;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
