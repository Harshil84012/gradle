package com.sungjun.requestDto;


public class UserRequestDto {

//    @NotEmpty(message = "email must not be empty")
//    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$", message = "email is invalid")
    private String username;

//    @NotEmpty(message = "password must not be empty")
//    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,12}$",message = "Password length should be min 8 and max 12 characters and it should contain at least one uppercase, one lowercase, one special character and one digit")
    private String password;

//    @NotEmpty(message = "firstname must not be empty")
    private String firstName;

//    @NotEmpty(message = "lastname must not be empty")
    private String lastName;

//    @NotEmpty(message = "role must not be empty")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRequestDto(String username, String password, String firstName, String lastName, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
