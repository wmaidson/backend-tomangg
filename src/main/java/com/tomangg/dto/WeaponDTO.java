package com.tomangg.dto;

public class WeaponDTO {

    private Long id;

    private String description;

    public WeaponDTO(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
