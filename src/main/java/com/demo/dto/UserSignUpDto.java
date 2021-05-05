package com.demo.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserSignUpDto {

    private String companyName;
    private String password;
    private String district;
    private String contactNumber;
    private String email;
    private List<String> role;
}
