package com.challengeraven.calculator.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "validemail.api")
public class EmailApiProperties {
	private String accessKey;
    private String baseUrl;
    private String path;
}
