package com.tomangg.controller;

import com.tomangg.model.ApiAuth;
import com.tomangg.repository.ApiAuthRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api-auth")
public class ApiAuthController {

    @Autowired
    private ApiAuthRepository apiAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<ApiAuth> createUser(@RequestBody ApiAuth apiAuth) {

        apiAuth.setPassword(passwordEncoder.encode(apiAuth.getPassword()));
        ApiAuth savedUser = apiAuthRepository.save(apiAuth);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<ApiAuth> getUser(@PathVariable String username) {
        Optional<ApiAuth> user = apiAuthRepository.findByUsername(username);

        if (user.isPresent()) {
            ApiAuth apiAuth = user.get();
            apiAuth.setPassword(null);
            return ResponseEntity.ok(apiAuth);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
