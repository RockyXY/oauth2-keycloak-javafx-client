package de.shardcraft.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
class CustomHttpRequest {

  private final Object body;
  private final HttpRequest request;

  static CustomHttpRequest forUrlEncodedPost(String uri, Object bodyParams) {
    String body = convertToUrlEncoded(bodyParams);
    HttpRequest request =
        createRequestBuilder(uri)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
    return new CustomHttpRequest(body, request);
  }

  static CustomHttpRequest forGet(String uri) {
    HttpRequest request = createRequestBuilder(uri).GET().build();
    return new CustomHttpRequest(null, request);
  }

  private static String convertToUrlEncoded(Object obj) {
    Map<String, String> map = new ObjectMapper().convertValue(obj, Map.class);

    return map.keySet().stream()
        .map(
            key -> {
              String value = String.valueOf(map.get(key));

              return value != null && value.length() > 0
                  ? key + "=" + URLEncoder.encode(value, StandardCharsets.UTF_8)
                  : null;
            })
        .filter(Objects::nonNull)
        .collect(Collectors.joining("&"));
  }

  private static HttpRequest.Builder createRequestBuilder(String uri) {
    try {
      return HttpRequest.newBuilder().uri(new URI(uri)).version(HttpClient.Version.HTTP_2);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean hasBody() {
    return body != null;
  }

  public boolean hasHeader() {
    return !request.headers().map().isEmpty();
  }

  public Map<String, List<String>> getHeader() {
    return request.headers().map();
  }
}
