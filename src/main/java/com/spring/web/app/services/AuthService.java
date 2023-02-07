package com.spring.web.app.services;

import com.spring.web.app.models.Role;
import com.spring.web.app.models.User;
import com.spring.web.app.models.repository.RoleRepository;
import com.spring.web.app.models.repository.UserRepository;
import com.spring.web.app.dto.request.LoginRequest;
import com.spring.web.app.dto.request.ChangePasswordRequest;
import com.spring.web.app.dto.response.JwtResponse;
import com.spring.web.app.security.jwt.JwtUtils;
import com.spring.web.app.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public void changePassword(ChangePasswordRequest cpr) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(cpr.getUserName(), cpr.getCurrentPassword()));

        if (!authentication.isAuthenticated())
            throw new RuntimeException("Can not authorize user to reset password!");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        user.setPassword(encoder.encode(cpr.getNewPassword()));
        userRepository.save(user);
    }

    public User registerUser(String username, String email, String password, String role) {
        if (userRepository.existsByUserName(username))
            throw new RuntimeException(String.format("Username %s has exists!", username));

        Role userRole = roleRepository.findByCode(role)
                .orElseThrow(() -> new RuntimeException(String.format("Role %s not found!", role)));

        User user = new User(
                null,
                username,
                email,
                encoder.encode(password),
                new HashSet<>()
        );

        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }
}
