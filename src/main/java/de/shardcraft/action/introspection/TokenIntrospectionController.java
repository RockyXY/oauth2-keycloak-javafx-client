package de.shardcraft.action.introspection;

import com.nimbusds.oauth2.sdk.ClientCredentialsGrant;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import de.shardcraft.action.ActionExecutor;
import de.shardcraft.keycloakconfig.KeycloakConfigManager;
import java.net.URI;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class TokenIntrospectionController {

  private final KeycloakConfigManager keycloakConfigManager;
  private final ActionExecutor actionExecutor;

  public TokenIntrospectionController(
      KeycloakConfigManager keycloakConfigManager, ActionExecutor actionExecutor) {
    this.keycloakConfigManager = keycloakConfigManager;
    this.actionExecutor = actionExecutor;
  }

  @FXML
  public void doAction() {

    String keycloakUri = keycloakConfigManager.getKeycloakUri();
    String realm = keycloakConfigManager.getRealm();
    URI tokenUri = URI.create(keycloakUri + "/" + realm + "/token");
    ClientSecretBasic clientCredentials =
        new ClientSecretBasic(
            new ClientID("frontend"), new Secret("Zyffm9PmZmM6f5oUuE0p3OGnlrChjXVy"));
    TokenRequest tokenRequest =
        new TokenRequest(tokenUri, clientCredentials, new ClientCredentialsGrant());
    HTTPResponse response = actionExecutor.execute(tokenRequest);
  }
}
