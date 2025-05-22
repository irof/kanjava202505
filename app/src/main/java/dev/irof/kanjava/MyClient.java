package dev.irof.kanjava;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyClient {

    private final RestTemplate restTemplate;

    public MyClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void callGet() {
        restTemplate.getForObject("http://localhost:8080/get", String.class);
    }
}
