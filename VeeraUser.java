package com.veera.web.security;

import com.veera.web.domain.duty.DutyTravelArranger;
import com.veera.web.domain.hr.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * VeeraUser
 */
public final class VeeraUser extends User {

	
	final private Employee user;
	
	/**
	 * VeeraUser constructor
	 * @param userName the user name
	 * @param grantedAuthorities the granted authorities
	 */
	public VeeraUser(Employee user, String userName, Collection<? extends GrantedAuthority> grantedAuthorities) {
		super(userName, "N/A", grantedAuthorities);
		this.user = user;
	}

	/**
	 * Get the employee
	 * @return the employee
	 */
	public Employee getUser() {
		return user;
	}
	
	
	/**
	 * Get the authenticated user
	 * @return a VeeraUser instance
	 */
	public static VeeraUser getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (VeeraUser) authentication.getPrincipal();
	}
}
