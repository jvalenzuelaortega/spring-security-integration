package com.integration.security.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "regex.expression")
@Getter
@Setter
public class RegexPropertiesConfig {

    private String email;
    private String password;
}
