package com.programacho.notemcphost;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("MCP Client starts the MCP Servers during context loading, which causes this test to fail.")
class NoteMcpHostApplicationTests {

     @Test
     void contextLoads() {
     }

}
