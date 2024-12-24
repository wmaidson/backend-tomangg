package com.tomangg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private int dkp;
    private int eventQuantity;
    private int guildBoss;
    private String weapon1;
    private String weapon2;
}

