package com.arpankundu.journalApp.DTO;

import com.arpankundu.journalApp.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

    private int id;
    private String username;

    @NotBlank(message = "Name should not be null")
    private String name;

    @NotBlank(message = "Password should not be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$"
            , message = "At least one lowercase letter [a-z]\n" +
            "    At least one uppercase letter [A-Z]\n" +
            "    At least one special character [@#$%^&+=]\n" +
            "    Minimum length of 8 characters.\n" +
            "    At least one digit [0-9]")
    private String password;

    private Role role;

    @NotBlank(message = "Email should not be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
            , message = "Please enter a valid email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @NotBlank(message = "Name should not be null") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name should not be null") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Password should not be null") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$"
            , message = "At least one lowercase letter [a-z]\n" +
            "    At least one uppercase letter [A-Z]\n" +
            "    At least one special character [@#$%^&+=]\n" +
            "    Minimum length of 8 characters.\n" +
            "    At least one digit [0-9]") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password should not be null") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$"
            , message = "At least one lowercase letter [a-z]\n" +
            "    At least one uppercase letter [A-Z]\n" +
            "    At least one special character [@#$%^&+=]\n" +
            "    Minimum length of 8 characters.\n" +
            "    At least one digit [0-9]") String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public @NotBlank(message = "Email should not be null") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
            , message = "Please enter a valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email should not be null") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
            , message = "Please enter a valid email") String email) {
        this.email = email;
    }
}
