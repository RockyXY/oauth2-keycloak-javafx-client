module oauth.keycloak.simulator {
  requires javafx.controls;
  requires javafx.fxml;

  opens de.shardcraft;
  opens de.shardcraft.token;
  opens de.shardcraft.action;
  opens de.shardcraft.action.introspection;
  opens de.shardcraft.keycloakconfig;
  opens de.shardcraft.console;
  opens de.shardcraft.requests;
  opens de.shardcraft.clients;
  opens de.shardcraft.ssl;

  requires static lombok;
  requires java.net.http;
  requires com.fasterxml.jackson.databind;
  requires org.json;
  requires oauth2.oidc.sdk;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.beans;
  requires spring.core;
}
