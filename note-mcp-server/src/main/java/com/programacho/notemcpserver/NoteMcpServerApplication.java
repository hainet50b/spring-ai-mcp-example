package com.programacho.notemcpserver;

import com.programacho.notemcpserver.application.NoteTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoteMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteMcpServerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider noteServiceProvider(NoteTools tools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(tools)
                .build();
    }
}
