package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {
    public static void main(String[] args) {
        System.out.println("The Api Result is !");
        String url="https://api.weatherbit.io/v2.0/current?lat=35.7796&lon=-78.6382&key=d09580e9668848c48d8a8ad86a26937e&include=minutely";

        HttpRequest request= HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient httpClient=HttpClient.newBuilder().build();
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(httpResponse.body());
    }
}