package de.shardcraft.httpclient;

import de.shardcraft.ConsoleManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class HttpRequestSender {

  public HttpResponse<String> get(String uri) {
    CustomHttpRequest customHttpRequest = CustomHttpRequest.forGet(uri);
    return sendRequest(customHttpRequest);
  }

  public HttpResponse<String> postFormUrlEncoded(String uri, Object bodyParams) {
    CustomHttpRequest customHttpRequest = CustomHttpRequest.forUrlEncodedPost(uri, bodyParams);
    return sendRequest(customHttpRequest);
  }

  private HttpResponse<String> sendRequest(CustomHttpRequest customHttpRequest) {
    try {
      ConsoleManager.getInstance().addText(new RequestRenderer().render(customHttpRequest));
      HttpResponse<String> response =
          HttpClient.newBuilder()
              .build()
              .send(customHttpRequest.getRequest(), HttpResponse.BodyHandlers.ofString());
      try {
        JSONObject jsonResponse = new JSONObject(response.body());
        ConsoleManager.getInstance().addText(jsonResponse.toString(2));
      } catch (JSONException ex) {
        ConsoleManager.getInstance().addText(response.body());
      }
      return response;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
