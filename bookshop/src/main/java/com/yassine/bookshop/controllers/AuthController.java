package com.yassine.bookshop.controllers;

import com.yassine.bookshop.dto.AuthUserDto;
import com.yassine.bookshop.dto.JwtResponseDto;
import com.yassine.bookshop.entities.User;
import com.yassine.bookshop.repositories.UserRepository;
import com.yassine.bookshop.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> auth(
            @Valid @RequestBody AuthUserDto authUserDto , HttpServletRequest request
    ){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authUserDto.getEmail(),
                            authUserDto.getPassword()
                    )
            );

            User user = userRepository.findByEmail(authUserDto.getEmail());
            if (user == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("error", "user not exists"));
            String tokenAccess = jwtService.generateAccessToken(user, request);
            String tokenRefresh = jwtService.generateRefreshToken(user, request);

            // for save refresh token in cookie
            ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenRefresh)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60)
                    .sameSite("Lax")
                    .build();

            // send refresh token in header
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new JwtResponseDto(tokenAccess));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return  null;
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh(
            @CookieValue(value = "refreshToken") String refreshToken , HttpServletRequest request) {
        if (!jwtService.validateToken(refreshToken))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("error", "the token is invalid"));
        String email = jwtService.getEmail(refreshToken);
        var user = userRepository.findByEmail(email);
        var accessToken = jwtService.generateAccessToken(user , request);
        return ResponseEntity.ok(new JwtResponseDto(accessToken));

    }


    @GetMapping("/me")
    public ResponseEntity<Long> me() {
        var authentication  = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        var email = authentication.getPrincipal();
        var user = userRepository.findByEmail((String) email);
        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.getId());
    }
}