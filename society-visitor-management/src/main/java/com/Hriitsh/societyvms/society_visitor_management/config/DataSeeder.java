package com.Hriitsh.societyvms.society_visitor_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Hriitsh.societyvms.society_visitor_management.entity.User;
import com.Hriitsh.societyvms.society_visitor_management.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN");
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("resident").isEmpty()) {
            User resident = new User("resident", passwordEncoder.encode("resident123"), "RESIDENT");
            userRepository.save(resident);
        }
    }
}
