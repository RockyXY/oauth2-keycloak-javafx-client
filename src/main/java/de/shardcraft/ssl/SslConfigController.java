package de.shardcraft.ssl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class SslConfigController {
  private final SslConfigManager sslConfigManager;
  @FXML public TextField truststorePathField;
  @FXML public TextField truststorePasswordField;

  public SslConfigController(SslConfigManager sslConfigManager) {
    this.sslConfigManager = sslConfigManager;
  }

  @FXML
  public void initialize() {
    truststorePathField.textProperty().bind(sslConfigManager.getSslConfig().getPathToTruststore());
    truststorePasswordField
        .textProperty()
        .bind(sslConfigManager.getSslConfig().getTrustStorePassword());
  }
}
