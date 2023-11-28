package com.example.demo.dto;

import com.example.demo.model.Sector;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PersonDTO {
    private Long id;
    @NotEmpty(message = "Login should not be empty")
    private String login;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "At least one sector is required")
    @Size(min = 1, max = 5)
    List<Sector> sectors;
}
