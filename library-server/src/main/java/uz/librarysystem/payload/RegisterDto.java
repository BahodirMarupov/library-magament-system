package uz.librarysystem.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterDto {
    @NotBlank(message = "Username should not be blank!")
    private String username;
    @NotBlank(message = "Password should not be blank!")
//    @Pattern(regexp ="((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})",message = "Not allowed password!")
    private String password;
    private String prePassword;

    public RegisterDto(@NotBlank(message = "Username should not be blank!") String username, @NotBlank(message = "Password should not be blank!") String password, String prePassword) {
        this.username = username;
        this.password = password;
        this.prePassword = prePassword;
    }

    public RegisterDto() {
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

    public String getPrePassword() {
        return prePassword;
    }

    public void setPrePassword(String prePassword) {
        this.prePassword = prePassword;
    }
}
