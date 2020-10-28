package com.dev.cinema.model.dto;

import com.dev.cinema.validation.Email;
import com.dev.cinema.validation.FieldsValueMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "passwordRepeat",
                message = "Passwords do not match!"
        )
})
public class UserRequestDto {
    @Email
    private String email;
    @NotNull(message = "Password can't be null")
    @Size(min = 4)
    private String password;
    private String passwordRepeat;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
