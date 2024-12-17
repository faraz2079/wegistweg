package de.fhdo.wegistweg;

import de.fhdo.wegistweg.Entity.User;
import de.fhdo.wegistweg.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import de.fhdo.wegistweg.Service.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("running");
        User customer = new User();
        customer.setUsername("customer");
        customer.setPassword(passwordEncoder.encode("password"));
        customer.setEmail("customer@example.com");
        customer.setRole("ROLE_CUSTOMER");
        userRepository.save(customer);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@example.com");
        admin.setRole("ROLE_ADMIN");
        userRepository.save(admin);
    }
}