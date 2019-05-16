package pl.edu.pw.mini.core.security.authorization;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tools.StringUtils;

import java.util.Collection;

public class AllowAllDecisionVoter implements AccessDecisionVoter<Object> {
    private static final String PREFIX = "ROLE";

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return StringUtils.anyEquals(attribute.getAttribute(), Constants.ANONYMOUS_ROLE, Constants.AUTHENTICATED_ROLE);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MethodInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        return attributes.stream().anyMatch(this::supports) ? ACCESS_GRANTED : ACCESS_ABSTAIN;
    }
}
