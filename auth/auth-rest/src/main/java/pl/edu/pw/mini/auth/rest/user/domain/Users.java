package pl.edu.pw.mini.auth.rest.user.domain;

import lombok.Data;
import pl.gutowskid.auth.api.UserRole;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "FORENAME")
    private String forename;

    @Column(name = "SURNAME")
    private String surname;
}
