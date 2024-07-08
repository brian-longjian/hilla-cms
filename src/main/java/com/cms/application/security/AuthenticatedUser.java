package com.cms.application.security;

import com.cms.application.data.User;
import com.cms.application.data.UserRepository;
import com.vaadin.flow.spring.security.AuthenticationContext;
import java.util.Optional;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<User> get() {
        // return authenticationContext.getAuthenticatedUser(UserDetails.class)
        // .map(userDetails ->
        // userRepository.findByUsername(userDetails.getUsername()));
        Object principal = authenticationContext.getAuthenticatedUser(Jwt.class).get();
        System.out.println(((Jwt) principal).getTokenValue());
        return authenticationContext.getAuthenticatedUser(Jwt.class)
                .map(jwt -> userRepository.findByUsername(jwt.getSubject()));
    }

    public void logout() {
        authenticationContext.logout();
    }

}
