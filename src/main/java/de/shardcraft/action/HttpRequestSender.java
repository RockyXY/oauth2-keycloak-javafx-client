package de.shardcraft.action;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import de.shardcraft.console.ConsoleManager;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestSender {

  private final ConsoleManager consoleManager;

  public HttpRequestSender(ConsoleManager consoleManager) {
    this.consoleManager = consoleManager;
  }

  public HTTPResponse send(HTTPRequest request) {
    try {
      consoleManager.addText(new RequestRenderer().render(request));
      HTTPResponse response = request.send();
      try {
        JSONObject jsonResponse = new JSONObject(response.getBody());
        consoleManager.addText(jsonResponse.toString(2));
      } catch (JSONException ex) {
        consoleManager.addText(response.getBody());
      }
      return response;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
