package de.shardcraft.httpclient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ResponseRenderer {

  public String render(HttpResponse<String> response) {
    StringBuilder result = new StringBuilder("Response" + System.lineSeparator());
    Map<String, List<String>> header = response.headers().map();
    if (!header.isEmpty()) {
      result.append(new HeaderRenderer().render(header));
    }
    if (response.body() != null && !response.body().isBlank()) {
      String prettyfiedJsonResponseBody = tryToPrettify(response.body());
      result.append(prettyfiedJsonResponseBody).append(System.lineSeparator());
    }
    return result.toString();
  }

  private String tryToPrettify(String jsonString) {
    try {
      JSONObject jo = new JSONObject(jsonString);
      return jo.toString(2);
    } catch (JSONException ex) {
      return jsonString;
    }
  }
}
