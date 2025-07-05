package com.example.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockConfig {
    private static WireMockServer wireMockServer;
    
    @BeforeClass
    public static void setUpWireMock() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);
        
        // Mock login API response
        stubFor(post(urlEqualTo("/api/login"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"success\": true, \"token\": \"mock-jwt-token\", \"user\": {\"id\": 1, \"username\": \"testuser\"}}")));
        
        // Mock dashboard API response
        stubFor(get(urlEqualTo("/api/dashboard"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"success\": true, \"data\": {\"welcome\": \"Welcome to dashboard\"}}")));
    }
    
    @AfterClass
    public static void tearDownWireMock() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
    
    public static String getMockServerUrl() {
        return "http://localhost:8089";
    }
} 