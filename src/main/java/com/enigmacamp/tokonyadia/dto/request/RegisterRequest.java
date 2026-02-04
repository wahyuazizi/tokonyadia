package com.enigmacamp.tokonyadia.dto.request;


import com.enigmacamp.tokonyadia.utils.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;

    private String fullname;
    private String email;
    private String address;
    private Gender gender;
}
