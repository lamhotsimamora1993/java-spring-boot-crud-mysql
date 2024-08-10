package net.pdrtechnology.mysql.mysql.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotEmpty(message = "The name is required")
    private String name;

    @NotEmpty(message = "The email is required")
    private String email;

    @Size(min=4,message = "the description sholud be at least 10 characters")
    @Size(max=255,message = "the description sholud be at most 255 characters")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
