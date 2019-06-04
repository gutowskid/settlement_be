package pl.edu.pw.mini.core.security.authorization;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.security.authentication.Context;

import java.util.Collection;
import java.util.stream.Stream;

public class AllowByRoleDecisionVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return Stream.of(Constants.AUTHENTICATED_ROLE, Constants.ANONYMOUS_ROLE).anyMatch(r -> r.equals(configAttribute.getAttribute()));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MethodInvocation.class.isAssignableFrom(aClass);
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        if(!(authentication instanceof Context)) {
            return ACCESS_ABSTAIN;
        }
        Context context = (Context) authentication;
        return collection.stream()
                .map(ConfigAttribute::getAttribute)
                .anyMatch(r -> r.equals(context.getPrincipal()))
                ? ACCESS_GRANTED : ACCESS_ABSTAIN;

    }
}
