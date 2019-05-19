package pl.edu.pw.mini.auth.api;

import lombok.Data;

@Data
public class LoginDto {
    private String login;
    private String password;
}
