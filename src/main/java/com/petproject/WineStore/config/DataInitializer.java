package com.petproject.WineStore.config;

import com.petproject.WineStore.model.Role;
import com.petproject.WineStore.model.User;
import com.petproject.WineStore.model.Wine;
import com.petproject.WineStore.model.WineType;
import com.petproject.WineStore.repository.UserRepository;
import com.petproject.WineStore.repository.WineRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WineRepository wineRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        addWines();
        addAdmin();
        addUsers();
    }

    public void addAdmin() {
        if (!userRepository.existsByEmail("admin@gmail.com")){
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setRoles(Collections.singleton(Role.ROLE_ADMIN));
            admin.setPassword(encoder.encode("1234567890"));
            admin.setActive(true);
            userRepository.save(admin);
        }
    }

    public void addUsers() {
        if(!userRepository.existsById(1L)) {
            for (int i = 1; i <= 10; i++) {
                User user = new User();
                user.setUsername("User" + i);
                user.setEmail("user" + i + "@gmail.com");
                user.setCity("Astana");
                user.setPhoneNumber("1234567890");
                user.setRoles(Collections.singleton(Role.ROLE_USER));
                user.setPassword(encoder.encode("1234567890"));
                user.setActive(true);
                userRepository.save(user);
            }
        }
    }

    public void addWines() {
        if (wineRepository.existsById(10L)) {
            return;
        }
        for (int i = 1; i <= 10; i++) {
            Wine wine = new Wine();
            wine.setName("Wine " + i);
            wine.setOrigin("Origin " + i);
            wine.setType(WineType.getRandomWineType());
            wine.setYear(2000 + i);
            wine.setPrice(10.00 * i);
            wine.setDescription("Description for wine " + i);
            wine.setStock(100 * i);
            wineRepository.save(wine);
        }
    }

}
