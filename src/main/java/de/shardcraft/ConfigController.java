package de.shardcraft;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConfigController {

  @FXML private TextField keylcoakUriField;
  @FXML private TextField realmField;

  @FXML
  public void initialize() {
    keylcoakUriField
        .textProperty()
        .bindBidirectional(KeycloakConfigManager.getInstance().getKeycloakUriProperty());
    realmField
        .textProperty()
        .bindBidirectional(KeycloakConfigManager.getInstance().getRealmProperty());
  }

  @FXML
  public void wellKnown() {
    ActionExecutor.execute(OAuth2Client::wellKnown);
  }
}
