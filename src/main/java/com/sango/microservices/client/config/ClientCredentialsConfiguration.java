package com.sango.microservices.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class ClientCredentialsConfiguration {

    @Value("${spring.security.oauth2.client.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${spring.security.oauth2.client.scope}")
    private String scope;

    @Bean
    @Qualifier("ClientCredentialsOAuth2FeignRequestInterceptor")
    public OAuth2FeignRequestInterceptor oauth2schemeRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), oauth2schemeResourceDetails());
    }

    @Bean
    public ClientCredentialsResourceDetails oauth2schemeResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setScope(Arrays.asList(scope.split(" ")));
        details.setGrantType("client_credentials");
        return details;
    }
}
