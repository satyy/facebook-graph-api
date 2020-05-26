package com.satyy.fb.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@NoArgsConstructor
@ConfigurationProperties("fb.api.group")
public class FacebookGroupConfig {
    private String nodeId;
    private String endpoint;
    private String accessToken;
}
