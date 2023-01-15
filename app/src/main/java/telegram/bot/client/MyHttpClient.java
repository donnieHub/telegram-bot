package telegram.bot.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MyHttpClient {

    private String uri;
    private String apiKey;

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

    public HttpClient createClient() {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
        return client;
    }

    public HttpRequest createGetRequest(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(java.net.URI.create(uri))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            //.header("X-Yandex-API-Key", getApiKeyFromPropertyFile())
            .GET()
            .build();
        return request;
    }

    public HttpRequest createGetRequest(String uri, String headerName, String headerValue) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(java.net.URI.create(uri))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            .header(headerName, headerValue)
            .GET()
            .build();
        return request;
    }

    public <T> HttpResponse<T> getApiResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<T> response =
            (HttpResponse<T>) client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
