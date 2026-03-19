package com.programacho.wikipediamcpserver;

import com.programacho.wikipediamcpserver.application.WikipediaTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WikipediaMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikipediaMcpServerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider wikipediaServiceProvider(WikipediaTools tools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(tools)
                .build();
    }
}