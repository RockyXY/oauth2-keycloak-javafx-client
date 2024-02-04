package de.shardcraft.action;

import com.nimbusds.oauth2.sdk.Request;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import de.shardcraft.console.ConsoleManager;
import org.springframework.stereotype.Component;

@Component
public class ActionExecutor {

  private final HttpRequestSender httpRequestSender;
  private final ConsoleManager consoleManager;

  public ActionExecutor(HttpRequestSender httpRequestSender, ConsoleManager consoleManager) {
    this.httpRequestSender = httpRequestSender;
    this.consoleManager = consoleManager;
  }

  public HTTPResponse execute(Request request) {
    return execute(request.toHTTPRequest());
  }

  public HTTPResponse execute(HTTPRequest request) {
    try {
      HTTPResponse response = httpRequestSender.send(request);
      consoleManager.addText(new ResponseRenderer().render(response));
      return response;
    } catch (Exception ex) {
      consoleManager.addText(ex.getMessage());
      throw new RuntimeException("Error executing request", ex);
    }
  }
}
