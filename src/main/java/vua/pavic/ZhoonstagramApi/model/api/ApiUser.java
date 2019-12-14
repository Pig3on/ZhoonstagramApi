package vua.pavic.ZhoonstagramApi.model.api;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ApiUser {
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Email
    private String password;
}
