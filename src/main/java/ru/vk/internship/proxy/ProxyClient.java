package ru.vk.internship.proxy;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ru.vk.internship.model.exception.ServiceFailureException;

@Component
@AllArgsConstructor
public class ProxyClient {
    private static final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    public <T> T get(String url, Class<T> responseType) {
        try {
            return restTemplate.getForObject(baseUrl + url, responseType);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceFailureException("Request to server is incorrect or server is unavailable");
        }
    }

    public <T> T post(String url, Object request, Class<T> responseType) {
        try {
            return restTemplate.postForObject(baseUrl + url, request, responseType);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceFailureException("Request to server is incorrect or server is unavailable");
        }
    }

    public <T> T put(String url, Object request, Class<T> responseType) {
        try {
            return restTemplate.exchange(baseUrl + url, HttpMethod.PUT, new HttpEntity<>(request), responseType).getBody();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceFailureException("Request to server is incorrect or server is unavailable");
        }
    }

    public void delete(String url) {
        try {
            restTemplate.delete(baseUrl + url);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            throw new ServiceFailureException("Request to server is incorrect or server is unavailable");
        }
    }
}
