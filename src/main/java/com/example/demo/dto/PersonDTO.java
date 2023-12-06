package com.example.demo.dto;

import com.example.demo.model.Sector;
import com.example.demo.validation.ValidationGroup;
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
    @NotEmpty(groups = ValidationGroup.Create.class, message = "Login should not be empty")
    private String login;
    @NotEmpty(groups = ValidationGroup.Create.class, message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(groups = ValidationGroup.Create.class, message = "Password should not be empty")
    private String password;
    @NotEmpty(groups = ValidationGroup.Update.class, message = "At least one sector is required")
    @Size(min = 1, max = 5)
    List<Sector> sectors;
}
