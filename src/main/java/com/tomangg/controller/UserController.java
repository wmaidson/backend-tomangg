package com.tomangg.controller;

import com.tomangg.dto.UserDTO;
import com.tomangg.model.User;
import com.tomangg.model.Weapon;
import com.tomangg.repository.UserRepository;
import com.tomangg.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getUsername(),
                        user.getDkp(),
                        user.getEventQuantity(),
                        user.getGuildBoss(),
                        user.getWeapon1() != null ? user.getWeapon1().getDescription() : null,
                        user.getWeapon2() != null ? user.getWeapon2().getDescription() : null
                ))
                .collect(Collectors.toList());
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<User>> createMultipleUsers(@RequestBody List<UserDTO> userDTOList) {
        List<User> users = new ArrayList<>();

        for (UserDTO userDTO : userDTOList) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setDkp(userDTO.getDkp());
            user.setEventQuantity(userDTO.getEventQuantity());
            user.setGuildBoss(userDTO.getGuildBoss());

            Weapon weapon1 = weaponRepository.findById(Long.parseLong(userDTO.getWeapon1())).orElse(null);
            Weapon weapon2 = weaponRepository.findById(Long.parseLong(userDTO.getWeapon2())).orElse(null);
            user.setWeapon1(weapon1);
            user.setWeapon2(weapon2);

            user.setPassword(BCrypt.hashpw("defaultPassword", BCrypt.gensalt()));
            users.add(user);
        }

        List<User> savedUsers = userRepository.saveAll(users);
        return ResponseEntity.ok(savedUsers);
    }
}
