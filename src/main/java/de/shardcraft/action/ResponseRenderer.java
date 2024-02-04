package de.shardcraft.action;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseRenderer {

  public String render(HTTPResponse response) {
    StringBuilder result = new StringBuilder("Response" + System.lineSeparator());
    if (response.getBody() != null && !response.getBody().isBlank()) {
      String prettyfiedJsonResponseBody = tryToPrettify(response.getBody());
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
