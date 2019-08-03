package com.sango.microservices.client.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class OauthService {

    public String getOauthTokenFromAuthServer(String plainClientCredentials) {
        String accessToken = null;
        RestTemplate restTemplate = new RestTemplate();

        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<>();
        parameters.add("username","john");
        parameters.add("password","123");
        parameters.add("grant_type","password");
        parameters.add("client_id","studentClientIdPassword");
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeader.add("Authorization","Basic "+ base64ClientCredentials);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(parameters,httpHeader);

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:9090/spring-security-oauth-server/oauth/token",
                                                                        httpEntity,
                                                                        Object.class);
        LinkedHashMap<String, Object> responseMap = (LinkedHashMap<String, Object>)responseEntity.getBody();

        if(responseMap!=null){
            accessToken = responseMap.get("access_token").toString();
            log.info("Access Token: {}",responseMap.get("access_token").toString());
            log.info("Token Type: {}",responseMap.get("token_type").toString());
            log.info("Refresh Token: {}",responseMap.get("refresh_token").toString());
            log.info("Expires In: {}",responseMap.get("expires_in").toString());
            log.info("Scope: {}",responseMap.get("scope").toString());
            log.info("Organization: {}",responseMap.get("organization").toString());
            log.info("Jti: {}",responseMap.get("jti").toString());
        }else{
            log.error("User Does not exists");
        }
        return accessToken;
    }
}
