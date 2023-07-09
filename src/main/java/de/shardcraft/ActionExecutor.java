package de.shardcraft;

import de.shardcraft.httpclient.ResponseRenderer;

import java.net.http.HttpResponse;
import java.util.function.Function;
import java.util.function.Supplier;

public class ActionExecutor {

  public static HttpResponse<String> execute(Supplier<HttpResponse<String>> httpRequestAction) {
    try {
      HttpResponse<String> response = httpRequestAction.get();
      ConsoleManager.getInstance().addText(new ResponseRenderer().render(response));
      return response;
    } catch (Exception ex) {
      ConsoleManager.getInstance().addText(ex.getMessage());
      throw new RuntimeException("Error executing request", ex);
    }
  }

  public static HttpResponse<String> execute(Function<OAuth2Client, HttpResponse<String>> action) {
    KeycloakConfigManager configManager = KeycloakConfigManager.getInstance();
    OAuth2Client oAuth2Client =
        new OAuth2Client(configManager.getKeycloakUri(), configManager.getRealm());
    return execute(() -> action.apply(oAuth2Client));
  }
}
