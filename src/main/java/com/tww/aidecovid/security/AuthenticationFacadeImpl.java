package com.tww.aidecovid.security;

import com.tww.aidecovid.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getAuthenticatedUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) getAuthentication().getPrincipal();
        return userDetails.getUser();
    }
}
