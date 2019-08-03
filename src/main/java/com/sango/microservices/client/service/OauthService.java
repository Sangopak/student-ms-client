package com.sango.microservices.client.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.security.oauth2.client.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    public String getOauthTokenFromAuthServer() {
        String accessToken = null;
        RestTemplate restTemplate = new RestTemplate();

        String base64ClientCredentials = new String(Base64.encodeBase64((clientId+":"+clientSecret).getBytes()));

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<>();
        //parameters.add("username","john");
        //parameters.add("password","123");
        //parameters.add("grant_type","password");
        /*For Password grant_type we need to pass the username and password along with client id, client secret but
        * for client credential grant_type (API to API ) we dont need to pass it, access token is provided using
        * the clientId and clientSecret as the Resource and the Client are owned by the same entity*/
        parameters.add("grant_type","client_credentials");
        parameters.add("client_id",clientId);
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeader.add("Authorization","Basic "+ base64ClientCredentials);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(parameters,httpHeader);

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(accessTokenUri,
                                                                        httpEntity,
                                                                        Object.class);
        LinkedHashMap<String, Object> responseMap = (LinkedHashMap<String, Object>)responseEntity.getBody();

        if(responseMap!=null){
            accessToken = responseMap.get("access_token").toString();
            log.info("Access Token: {}",accessToken);
        }else{
            log.error("User Does not exists");
        }
        return accessToken;
    }
}
