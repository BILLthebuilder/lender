package com.lending.Util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SendSms {
    public static void sendSms(String phoneNumber, String message) {
        // Example sms API url
        String apiUrl = "http://example.com/smsapi";

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Sample JSON request body
        String requestBody = "{\"phone\": \"" + phoneNumber + "\", \"message\": \"" + message + "\"}";

        // Create an HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        // Process the response
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("SMS sent successfully.");
        } else {
            System.out.println("Failed to send SMS. Response code: " + responseEntity.getStatusCodeValue());
            System.out.println("Response body: " + responseEntity.getBody());
        }
    }

    // Example usage
    public static void main(String[] args) {
        String phoneNumber = "+254722123456";
        String message = "Hello, this is a test message.";
        sendSms(phoneNumber, message);
    }
}
