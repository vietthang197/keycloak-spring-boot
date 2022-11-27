package com.ex.controller;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/film")
public class FilmController {
    @GetMapping("{id}")

    public String getFilm(@PathVariable String id, HttpServletRequest request) {

        return "OK";
    }
}
