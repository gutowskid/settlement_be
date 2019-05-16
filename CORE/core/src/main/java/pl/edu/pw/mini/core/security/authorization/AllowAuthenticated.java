package pl.edu.pw.mini.core.security.authorization;

import org.springframework.security.access.annotation.Secured;
import pl.edu.pw.mini.core.configuration.Constants;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention( RUNTIME )
@Target( METHOD )
@Secured(value = {Constants.AUTHENTICATED_ROLE})
@Inherited
public @interface AllowAuthenticated {
}
