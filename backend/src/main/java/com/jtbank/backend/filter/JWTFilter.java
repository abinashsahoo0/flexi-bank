package com.jtbank.backend.filter;

import com.jtbank.backend.service.IJWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final IJWTService service;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var path = request.getRequestURI();

        if(path.contains("login") || path.contains("register") || path.contains("create")) {
            filterChain.doFilter(request, response);
        }

        String token = request.getHeader("Authorization");
        String accountNumber = service.validateToken(token.substring(7));
        request.setAttribute("accountNumber", accountNumber);

        filterChain.doFilter(request, response);
    }
}
