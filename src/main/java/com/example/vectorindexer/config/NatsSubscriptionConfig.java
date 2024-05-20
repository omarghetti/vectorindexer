package com.example.vectorindexer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "nats")
public class NatsSubscriptionConfig {
    
}
