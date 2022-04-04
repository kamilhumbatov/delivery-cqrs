package com.delivery.user.service.impl;

import com.delivery.user.domain.User;
import com.delivery.user.dto.signup.SignUpRequest;
import com.delivery.user.exception.UsernameAlreadyTakenException;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.service.RoleService;
import com.delivery.user.service.UserService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private static final String YOUR_REGISTRATION_WAS_SUCCESSFUL = "Your registration was successful!";

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public String createCustomer(SignUpRequest request) {
        return createAccount(request, RoleName.ROLE_CUSTOMER);
    }

    @Override
    public String createCourier(SignUpRequest request) {
        return createAccount(request, RoleName.ROLE_COURIER);
    }

    private String createAccount(SignUpRequest request, String role) {
        Optional<User> userOptional = findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            throw new UsernameAlreadyTakenException();
        } else {
            var user = User.builder()
                    .fullName(request.getFullName())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(roleService.findByDescription(role))
                    .build();
            repository.save(user);
            return YOUR_REGISTRATION_WAS_SUCCESSFUL;
        }
    }

}
