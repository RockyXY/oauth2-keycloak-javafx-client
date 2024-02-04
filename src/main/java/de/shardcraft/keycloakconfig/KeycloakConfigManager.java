package de.shardcraft.keycloakconfig;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KeycloakConfigManager {

  private final StringProperty keycloakUriProperty =
      new SimpleStringProperty("http://localhost:8280");
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
