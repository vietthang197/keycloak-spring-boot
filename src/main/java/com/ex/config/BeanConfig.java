package com.ex.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.InputStream;
import java.util.Arrays;

@Configuration
public class BeanConfig {
    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
//        return new KeycloakConfigResolver() {
//
//            private KeycloakDeployment keycloakDeployment;
//
//            @Override
//            public KeycloakDeployment resolve(HttpFacade.Request facade) {
//                if (keycloakDeployment != null) {
//                    return keycloakDeployment;
//                }
//
//                String path = "keycloak_bak.json";
//                InputStream configInputStream = getClass().getClassLoader().getResourceAsStream(path);
//
//                if (configInputStream == null) {
//                    throw new RuntimeException("Could not load Keycloak deployment info: " + path);
//                } else {
//                    keycloakDeployment = KeycloakDeploymentBuilder.build(configInputStream);
//                }
//
//                return keycloakDeployment;
//            }
//        };
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    @Autowired
    public KeycloakClientRequestFactory keycloakClientRequestFactory;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public KeycloakRestTemplate keycloakRestTemplate() {
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }

    @Bean
    @Primary
    public KeycloakSpringBootProperties properties() {
        final KeycloakSpringBootProperties props = new KeycloakSpringBootProperties();
        final PolicyEnforcerConfig policyEnforcerConfig = new PolicyEnforcerConfig();
        policyEnforcerConfig.setEnforcementMode(PolicyEnforcerConfig.EnforcementMode.ENFORCING);
        policyEnforcerConfig.setUserManagedAccess(new PolicyEnforcerConfig.UserManagedAccessConfig());
        props.setPolicyEnforcerConfig(policyEnforcerConfig);
        return props;
    }
}
