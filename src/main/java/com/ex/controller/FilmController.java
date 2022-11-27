package com.ex.controller;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.authorization.UserManagedPermissionUtil;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.permission.evaluator.PermissionEvaluator;
import org.keycloak.representations.idm.authorization.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/film")
public class FilmController {
    @GetMapping("{id}")
    public String getFilm(@PathVariable String id, HttpServletRequest request) {
        KeycloakSecurityContext keycloakSecurityContext =
                (KeycloakSecurityContext) request
                        .getAttribute(KeycloakSecurityContext.class.getName());
        AuthorizationContext authzContext =
                keycloakSecurityContext.getAuthorizationContext();
        System.out.println("AUthzContext: " + authzContext);
        return "OK";
    }
}
