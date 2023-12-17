package com.attech.controller;


import com.attech.dto.request.UserRequest;
import com.attech.dto.response.AuthResponse;
import com.attech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest authRequest){
        AuthResponse register = authService.register(authRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(register);
    }
}
