package com.mcnichol.demo.config;

import brave.propagation.CurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CurrentTraceContext slf4jSpanLogger() {
        return CustomTraceContext.create();
    }
}