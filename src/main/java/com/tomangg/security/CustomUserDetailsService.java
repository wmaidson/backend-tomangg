package com.tomangg.security;

import com.tomangg.model.ApiAuth;
import com.tomangg.repository.ApiAuthRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ApiAuthRepository apiAuthRepository;

    public CustomUserDetailsService(ApiAuthRepository apiAuthRepository) {
        this.apiAuthRepository = apiAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiAuth apiAuth = apiAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(apiAuth.getUsername())
                .password(apiAuth.getPassword())
                .roles(apiAuth.getRole())
                .build();
    }
}