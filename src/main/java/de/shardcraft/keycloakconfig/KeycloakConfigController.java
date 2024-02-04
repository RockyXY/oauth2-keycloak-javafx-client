package de.shardcraft.keycloakconfig;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import de.shardcraft.action.ActionExecutor;
import java.net.URI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConfigController {

  private final KeycloakConfigManager keycloakConfigManager;
  private final ActionExecutor actionExecutor;
  @FXML private TextField keylcoakUriField;
  @FXML private TextField realmField;

  public KeycloakConfigController(
      KeycloakConfigManager keycloakConfigManager, ActionExecutor actionExecutor) {
    this.keycloakConfigManager = keycloakConfigManager;
    this.actionExecutor = actionExecutor;
  }

  @FXML
  public void initialize() {
    keylcoakUriField
        .textProperty()
        .bindBidirectional(keycloakConfigManager.getKeycloakUriProperty());
    realmField.textProperty().bindBidirectional(keycloakConfigManager.getRealmProperty());
  }

  @FXML
  public void wellKnown() {
    HTTPRequest request =
        new HTTPRequest(
            HTTPRequest.Method.GET,
            URI.create(
                keylcoakUriField.getText()
                    + "/realms/"
                    + realmField.getText()
                    + "/.well-known/openid-configuration"));
    actionExecutor.execute(request);
  }
}
