package com.tww.aidecovid.security;

import com.tww.aidecovid.model.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
    User getAuthenticatedUser();
}
