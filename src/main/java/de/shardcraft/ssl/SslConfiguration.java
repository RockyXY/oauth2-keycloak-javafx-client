package de.shardcraft.ssl;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class SslConfiguration {

  private SimpleStringProperty pathToTruststore =
      new SimpleStringProperty("C:\\Development\\keycloak-21.0.1\\client.truststore");
  private SimpleStringProperty trustStorePassword = new SimpleStringProperty("client");
}
