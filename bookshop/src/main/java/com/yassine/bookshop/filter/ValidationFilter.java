package com.yassine.bookshop.filter;


import com.yassine.bookshop.entities.Role;
import com.yassine.bookshop.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class ValidationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,  HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            var tokenWithoutBearer = authHeader.replace("Bearer ", "");
            if (!jwtService.validateToken(tokenWithoutBearer)){
                filterChain.doFilter(request, response);
                return;
            }

            String ipInput = jwtService.extractClaim(tokenWithoutBearer , "user_ip");
            String ipRequest = request.getRemoteAddr();
            System.out.println("IP from token: " + ipInput);
            System.out.println("IP from request: " + ipRequest);
            if (!ipInput.equals(ipRequest)){
                throw new BadCredentialsException("Token stolen or IP changed!");
            }
            Role role = jwtService.getRole(tokenWithoutBearer);
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            var authentication = new UsernamePasswordAuthenticationToken(
                    jwtService.getEmail(tokenWithoutBearer),
                    null,
                    authorities
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(
                    authentication
            );
            filterChain.doFilter(request, response);

    }
}
