package com.example.Linkdlyst.Utils.Services;

import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailService {
    private final RestClient restClient;
    private final String apiKey;
    private final String fromEmail;
    private final String fromName;

    public EmailService(
            @Value("${sendgrid.api-key}") String apiKey,
            @Value("${sendgrid.from-email}") String fromEmail,
            @Value("${sendgrid.from-name}") String fromName) {
        this.apiKey = apiKey;
        this.fromEmail = fromEmail;
        this.fromName = fromName;

        restClient = RestClient.builder()
                .baseUrl("https://api.sendgrid.com/v3")
                .build();
    }

    public boolean sendOtp(String email, String Otp) {
        Map<String, Object> body = Map.of(
                "personalizations", List.of(
                        Map.of(
                                "to", List.of(
                                        Map.of("email", email)))),
                "from", Map.of(
                        "email", fromEmail,
                        "name", fromName ),
                "subject", "Your Linkdlyst verification code",
                "content", List.of(
                        Map.of(
                                "type", "text/plain",
                                "value", "Your Linkdlyst verification code is: " + Otp)));

        restClient.post()
                .uri("/mail/send")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(body)
                .retrieve()
                .toBodilessEntity();

        return true;
    }
}
