package com.programacho.wikipediamcpserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://en.wikipedia.org/w/rest.php/v1")
                .defaultHeader("User-Agent", "wikipedia-mcp-server (learning project)")
                .build();
    }
}
