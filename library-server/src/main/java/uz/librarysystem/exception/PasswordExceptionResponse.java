package uz.librarysystem.exception;

public class PasswordExceptionResponse {
    private String prePassword;

    public PasswordExceptionResponse() {
    }

    public PasswordExceptionResponse(String prePassword) {
        this.prePassword = prePassword;
    }

    public String getPrePassword() {
        return prePassword;
    }

    public void setPrePassword(String prePassword) {
        this.prePassword = prePassword;
    }
}
