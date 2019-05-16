package pl.gutowskid.auth.api;

import lombok.Data;
import pl.edu.pw.mini.model.UserRole;

@Data
public class AddUserDto {
    private String userId;
    private UserRole role;
    private String forename;
    private String surname;
}
