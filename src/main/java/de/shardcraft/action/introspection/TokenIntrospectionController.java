package de.shardcraft.action.introspection;

import com.nimbusds.oauth2.sdk.TokenIntrospectionRequest;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import de.shardcraft.action.ActionExecutor;
import de.shardcraft.clients.ClientEntry;
import de.shardcraft.clients.ClientEntryCell;
import de.shardcraft.clients.ClientManager;
import de.shardcraft.console.ConsoleManager;
import de.shardcraft.keycloakconfig.KeycloakConfigManager;
import de.shardcraft.token.TokenEntry;
import de.shardcraft.token.TokenEntryCell;
import de.shardcraft.token.TokenManager;
import java.net.URI;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.stereotype.Component;

@Component
public class TokenIntrospectionController {

  private final KeycloakConfigManager keycloakConfigManager;
  private final ActionExecutor actionExecutor;
  private final TokenManager tokenManager;
  private final ClientManager clientManager;
  private final ConsoleManager consoleManager;
  @FXML public ComboBox<TokenEntry> tokenComboBox;
  @FXML public ComboBox<ClientEntry> clientComboBox;

  public TokenIntrospectionController(
      KeycloakConfigManager keycloakConfigManager,
      ActionExecutor actionExecutor,
      TokenManager tokenManager,
      ClientManager clientManager,
      ConsoleManager consoleManager) {
    this.keycloakConfigManager = keycloakConfigManager;
    this.actionExecutor = actionExecutor;
    this.tokenManager = tokenManager;
    this.clientManager = clientManager;
    this.consoleManager = consoleManager;
  }

  @FXML
  public void initialize() {
    tokenComboBox.setCellFactory(param -> new TokenEntryCell());
    tokenComboBox.setButtonCell(new TokenEntryCell());
    tokenComboBox.itemsProperty().bindBidirectional(tokenManager.getTokenList());

    FilteredList<ClientEntry> filteredClientEntries =
        new FilteredList<>(clientManager.getClientList());
    filteredClientEntries.setPredicate(
        entry -> !ClientEntry.newClient.getClientId().equals(entry.getClientId()));
    clientComboBox
        .itemsProperty()
        .bindBidirectional(new SimpleListProperty<>(filteredClientEntries));
    clientComboBox.setButtonCell(new ClientEntryCell());
    clientComboBox.setCellFactory(param -> new ClientEntryCell());
  }

  @FXML
  public void doAction() {
    try {
      String keycloakUri = keycloakConfigManager.getKeycloakUri();
      String realm = keycloakConfigManager.getRealm();
      URI introspectionUri =
          URI.create(
              keycloakUri + "/realms/" + realm + "/protocol/openid-connect/token/introspect");

      ClientEntry selectedItem = clientComboBox.getSelectionModel().getSelectedItem();
      ClientID clientID = new ClientID(selectedItem.getClientId());
      Secret secret = new Secret(selectedItem.getSecret());

      ClientSecretBasic clientCredentials = new ClientSecretBasic(clientID, secret);
      TokenIntrospectionRequest request =
          new TokenIntrospectionRequest(
              introspectionUri, clientCredentials, tokenComboBox.getValue().getToken());
      actionExecutor.execute(request);
    } catch (Exception e) {
      consoleManager.addText(e.getMessage() + System.lineSeparator());
    }
  }
}
