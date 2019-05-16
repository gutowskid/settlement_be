package pl.gutowskid.auth.api;

import lombok.Data;

@Data
public class LoginDto {
    private String login;
    private String password;
}
