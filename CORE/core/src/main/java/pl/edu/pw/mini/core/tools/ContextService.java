package pl.edu.pw.mini.core.tools;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.pw.mini.core.security.authentication.Context;

public class ContextService {

    public static Context getContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof Context) {
            return (Context) authentication;
        } else {
            throw new RuntimeException("AUTHORIZATION ERROR!");
        }
    }
}
