package com.tomangg.repository;

import com.tomangg.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}