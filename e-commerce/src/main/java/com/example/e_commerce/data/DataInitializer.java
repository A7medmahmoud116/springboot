package com.example.e_commerce.data;

import com.example.e_commerce.model.Role;
import com.example.e_commerce.model.User;
import com.example.e_commerce.repository.RoleRepository;
import com.example.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_USER", "ROLE_ADMIN");
        createDefaultRoleIfNotExists(defaultRoles);
        createDefaultUsers();
        createDefaultAdmin();
    }

    private void createDefaultUsers() {
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        for (int i = 1; i <= 5; i++) {
            String email = "user" + i + "@example.com";
            if (userRepository.existsByEmail(email)) {
                continue;
            }
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Doe");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
            System.out.println("User " + email + " created");
        }
    }
    private void createDefaultAdmin() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
        for (int i = 1; i <= 2; i++) {
            String email = "admin" + i + "@example.com";
            if (userRepository.existsByEmail(email)) {
                continue;
            }
            User user = new User();
            user.setFirstName("Ahmed");
            user.setLastName("Mahmoud");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(Set.of(adminRole));
            userRepository.save(user);
            System.out.println("Admin " + email + " created");
        }
    }



    private void createDefaultRoleIfNotExists(Set<String> roles) {
        roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role::new).forEach(roleRepository::save);

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
