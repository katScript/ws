package com.spring.web.app.controller;

import javax.validation.Valid;

import com.spring.web.app.dto.request.ChangePasswordRequest;
import com.spring.web.app.dto.request.LoginRequest;
import com.spring.web.app.dto.request.RegisterRequest;
import com.spring.web.app.dto.response.JwtResponse;
import com.spring.web.app.models.Role;
import com.spring.web.app.services.AuthService;
import com.spring.web.app.dto.errors.ErrorResponse;
import com.spring.web.app.dto.message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            authService.registerUser(
                    registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    registerRequest.getPassword(),
                    Role.USER
            );
            return ResponseEntity.ok(new MessageResponse(String.format("Register customer success with username: %s !", registerRequest.getUsername())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    400,
                    e.getMessage(),
                    "Contact to admin for more information!"
            ));
        }
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            authService.registerUser(
                    registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    registerRequest.getPassword(),
                    Role.ADMIN
            );
            return ResponseEntity.ok(new MessageResponse(String.format("Register admin success with username: %s !", registerRequest.getUsername())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    400,
                    e.getMessage(),
                    "Contact to admin for more information!"
            ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwtResponse = authService.login(loginRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    400,
                    e.getMessage(),
                    "Contact to admin for more information!"
            ));
        }
    }

    @PostMapping("/password/change")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            authService.changePassword(changePasswordRequest);
            return ResponseEntity.ok(new MessageResponse("Update password success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(
                    400,
                    e.getMessage(),
                    "Contact to admin for more information!")
            );
        }
    }
}
