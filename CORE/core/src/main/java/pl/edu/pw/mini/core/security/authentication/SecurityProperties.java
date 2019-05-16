package pl.edu.pw.mini.core.security.authentication;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="pl.edu.pw.mini.security")
public class SecurityProperties {
    private String issuer;
    private Long tokenValidity;
    private String secretKey;
}
