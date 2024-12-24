package com.tomangg.controller;

import com.tomangg.dto.WeaponDTO;
import com.tomangg.model.Weapon;
import com.tomangg.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weapons")
public class WeaponController {

    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping
    public List<WeaponDTO> getAllWeapons() {
        return weaponRepository.findAll().stream()
                .map(weapon -> new WeaponDTO(weapon.getId(), weapon.getDescription()))
                .toList();
    }

    @PostMapping
    public Weapon createWeapon(@RequestBody Weapon weapon) {
        return weaponRepository.save(weapon);
    }
}
