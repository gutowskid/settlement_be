package pl.edu.pw.mini.core.security.authentication;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter(AccessLevel.PACKAGE)
public class Context implements Authentication {
    private String userId;
    private String role;
    private Token token;
    private String ip;
    private String foreName;
    private String surName;
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getCredentials() {
        return token.getTokenString();
    }

    @Override
    public Token getDetails() {
        return token;
    }

    @Override
    public String getPrincipal() {
        return role;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return userId;
    }
}
