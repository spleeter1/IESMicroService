package com.api_gateway.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// JwtProperties.java
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtProperties {
    private String secret;
    private Long expiration;

}

