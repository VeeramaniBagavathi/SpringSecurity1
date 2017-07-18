package com.veera.web.security;

import com.veera.web.domain.geo.Country;
import com.veera.web.domain.Employee;
import com.veera.web.domain.Psn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Creates a user details object based by combining the information from the pre authenticated authentication token<br>
 * i.e. the roles together with the information from the employee.
 */
public class PreAuthenticatedUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreAuthenticatedUserDetailsService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(((GrantedAuthoritiesContainer) token.getDetails()).getGrantedAuthorities());

        GrantedAuthority roleATA = getRoleATA(authorities);
        Psn psn = new Psn(token.getName());
        Employee user = new Employee();
        user.setCountry(new Country("nl"));
        user.setFirstName("Veeramani");
        user.setEmailAddress("Veeera@gmail.com");

        LOGGER.info("Granted Authorities [{}] for employee {}  UID {}", authorities, psn, token.getName());

        return new VeeraUser(user, token.getName(), authorities);
    }
    private GrantedAuthority getRoleATA(final Collection<GrantedAuthority> authorities) {
        GrantedAuthority grantedAuthorityATA = null;

        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(veeraRole.ATA.toString())) {
                grantedAuthorityATA = grantedAuthority;
                break;
            }
        }
        return grantedAuthorityATA;
    }

}
