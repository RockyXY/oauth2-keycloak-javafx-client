package de.shardcraft;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KeycloakConfigManager {

  private static final KeycloakConfigManager instance = new KeycloakConfigManager();

  public static KeycloakConfigManager getInstance() {
    return instance;
  }

  private final StringProperty keycloakUriProperty = new SimpleStringProperty("http://localhost:8280");
  private final StringProperty realmProperty = new SimpleStringProperty("myrealm");

  public StringProperty getKeycloakUriProperty() {
    return keycloakUriProperty;
  }

  public StringProperty getRealmProperty() {
    return realmProperty;
  }

  public String getKeycloakUri() {
    return keycloakUriProperty.get();
  }

  public String getRealm() {
    return realmProperty.get();
  }
}
