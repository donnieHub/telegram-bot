package telegram.bot.client;

import static java.time.Duration.ofMinutes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class MyHttpClient {

    private String uri;
    private String apiKey;
    private static final String JSON_CONTENT_TYPE = "application/json";

    public MyHttpClient(String uri, String apiKey) {
        this.uri = uri;
        this.apiKey = apiKey;
    }


    public String createURI (String url, String... params) {
        StringBuilder uri = new StringBuilder(url);
        for (String param : params) {
            uri.append(param);
        }
        return uri.toString();
    }

    public <T> HttpClient createClient() {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
        return client;
    }

    public HttpRequest createGetRequest(String uri) {
        return createGetRequest(uri, "Content-Type", JSON_CONTENT_TYPE);
    }

    public HttpRequest createGetRequest(String uri, String... headerValue) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .timeout(ofMinutes(1))
            .headers(headerValue)
            .GET()
            .build();
        return request;
    }

    public <T> Optional<HttpResponse<T>> getApiResponse(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<T> response = (HttpResponse<T>) client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending request to " + uri + ": " + e.getMessage());
            return Optional.empty();
        }
    }

}
