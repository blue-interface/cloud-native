package com.polarbookshop.edgeservice.user;

import com.polarbookshop.edgeservice.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class UserController {
    @RequestMapping("user")
    public Mono<User> getUser(
            @AuthenticationPrincipal OidcUser oidcUser
    ) {
        var user = new User(
                        oidcUser.getPreferredUsername(),
                        oidcUser.getGivenName(),
                        oidcUser.getFamilyName(),
                        List.of("employee", "customer"));

        return Mono.just(user);
    }
}
