module oauth.keycloak.simulator {
  requires javafx.controls;
  requires javafx.fxml;

  opens de.shardcraft to
      javafx.fxml,
      com.fasterxml.jackson.databind;

  exports de.shardcraft;

  exports de.shardcraft.httpclient;

  opens de.shardcraft.httpclient to
      com.fasterxml.jackson.databind,
      javafx.fxml;
  exports de.shardcraft.token;
  opens de.shardcraft.token to com.fasterxml.jackson.databind, javafx.fxml;

  requires static lombok;
  requires java.net.http;
  requires com.fasterxml.jackson.databind;
  requires org.json;
}
