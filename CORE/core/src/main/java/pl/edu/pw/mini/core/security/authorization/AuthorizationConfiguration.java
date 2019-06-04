package pl.edu.pw.mini.core.security.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import pl.edu.pw.mini.core.tools.FluentListBuilder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class AuthorizationConfiguration extends GlobalMethodSecurityConfiguration {

    @Override
    public AccessDecisionManager accessDecisionManager() {
        return new AffirmativeBased(
        FluentListBuilder.<AccessDecisionVoter<? extends Object>>aList()
                .with(new AllowAllDecisionVoter())
                .with(new AllowAuthenticatedDecisionVoter())
                .with(new AllowByRoleDecisionVoter())
                .build());
    }
}
