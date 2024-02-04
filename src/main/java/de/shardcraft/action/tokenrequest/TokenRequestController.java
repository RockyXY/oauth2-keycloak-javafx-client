package de.shardcraft.action.tokenrequest;

import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.nimbusds.oauth2.sdk.ClientCredentialsGrant;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import de.shardcraft.action.ActionExecutor;
import de.shardcraft.clients.ClientEntry;
import de.shardcraft.clients.ClientEntryCell;
import de.shardcraft.clients.ClientManager;
import de.shardcraft.console.ConsoleManager;
import de.shardcraft.keycloakconfig.KeycloakConfigManager;
import de.shardcraft.token.TokenEntry;
import de.shardcraft.token.TokenManager;
import java.net.URI;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class TokenRequestController {

  private final KeycloakConfigManager keycloakConfigManager;
  private final ActionExecutor actionExecutor;
  private final TokenManager tokenManager;
  private final ClientManager clientManager;
  private final ConsoleManager consoleManager;
  @FXML public ComboBox<ClientEntry> clientComboBox;
  @FXML public TextField tokenNameField;

  public TokenRequestController(
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
      URI tokenUri =
          URI.create(keycloakUri + "/realms/" + realm + "/protocol/openid-connect/token");

      ClientEntry selectedItem = clientComboBox.getSelectionModel().getSelectedItem();
      ClientID clientID = new ClientID(selectedItem.getClientId());
      Secret secret = new Secret(selectedItem.getSecret());

      ClientSecretBasic clientCredentials = new ClientSecretBasic(clientID, secret);
      TokenRequest request =
          new TokenRequest(tokenUri, clientCredentials, new ClientCredentialsGrant());
      HTTPResponse response = actionExecutor.execute(request);
      TokenResponse tokenResponse = TokenResponse.parse(response);
      if (tokenResponse.indicatesSuccess()) {
        AccessTokenResponse successResponse = tokenResponse.toSuccessResponse();
        String tokenName = tokenNameField.getText();
        tokenManager.addToken(
            new TokenEntry(tokenName + " (Access)", successResponse.getTokens().getAccessToken()));
        RefreshToken refreshToken = successResponse.getTokens().getRefreshToken();
        if (refreshToken != null) {
          tokenManager.addToken(new TokenEntry(tokenName + " (Refresh)", refreshToken));
        }
      }
    } catch (Exception e) {
      consoleManager.addText(e.getMessage() + System.lineSeparator());
    }
  }
}
