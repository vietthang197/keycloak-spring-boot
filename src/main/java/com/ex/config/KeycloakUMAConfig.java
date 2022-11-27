package com.ex.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

@Configuration
public class KeycloakUMAConfig {

    public KeycloakUMAConfig(KeycloakSpringBootProperties keycloakSpringBootProperties) throws NoSuchFieldException, IllegalAccessException {
        keycloakSpringBootProperties.getPolicyEnforcerConfig().setUserManagedAccess(new PolicyEnforcerConfig.UserManagedAccessConfig());
    }
}
