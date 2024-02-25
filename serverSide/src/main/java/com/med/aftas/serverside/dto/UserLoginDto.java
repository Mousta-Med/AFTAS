package com.med.aftas.serverside.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotBlank(message = "username should be not Empty")
    private String username;

    @NotBlank(message = "password should be not Empty")
    private String password;
}
