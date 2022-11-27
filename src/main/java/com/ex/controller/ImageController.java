package com.ex.controller;

import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springboot.KeycloakBaseSpringBootConfiguration;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {


    @GetMapping("{id}/view")
    public String viewImage(@PathVariable String id, HttpServletRequest request) {

        return "view-OK";
    }

    @DeleteMapping("{id}/delete")
    public String deleteImage(@PathVariable String id, HttpServletRequest request) {

        return "Delete-OK";
    }

    @PostMapping
    public String createImage() {
        String resourceId = UUID.randomUUID().toString();
        AuthzClient authzClient = AuthzClient.create();
        ResourceRepresentation resourceRepresentation = new ResourceRepresentation();
        resourceRepresentation.setId(resourceId);
        resourceRepresentation.setName(resourceId);
        resourceRepresentation.setDisplayName("Anh nude: " + resourceId);
        resourceRepresentation.setOwnerManagedAccess(true);

        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        resourceRepresentation.setOwner(userId);
        resourceRepresentation.addScope("view", "edit");

        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        authzClient.protection(context.getTokenString()).resource().create(resourceRepresentation);
        return "create-OK:id=" + resourceId;
    }
}
