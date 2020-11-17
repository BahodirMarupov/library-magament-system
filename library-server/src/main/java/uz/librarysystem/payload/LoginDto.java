package uz.librarysystem.payload;

import javax.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = "Username should not be blank!")
    private String username;
    @NotBlank(message = "Password should not be blank!")
    private String password;

    public LoginDto() {
    }

    public LoginDto(@NotBlank(message = "Username should not be blank!") String username, @NotBlank(message = "Password should not be blank!") String password) {
        this.username = username;
        this.password = password;
    }

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
}
