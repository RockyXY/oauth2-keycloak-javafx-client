package de.shardcraft;

import de.shardcraft.token.TokenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.net.http.HttpResponse;

public class GetTokenController {

  @FXML public TextField tokenNameField;
  @FXML public TextField clientIdField;
  @FXML public TextField clientSecretField;

  @FXML
  public void getToken() {
    ClientCredentials clientCredentials = new ClientCredentials();
    clientCredentials.setClientId(clientIdField.getText());
    clientCredentials.setSecret(clientSecretField.getText());
    HttpResponse<String> response =
        ActionExecutor.execute((client) -> client.getAccessToken(clientCredentials));
    JSONObject jsonResponse = new JSONObject(response.body());
    String accessTokenValue = jsonResponse.get("access_token").toString();
    TokenManager.getInstance().addToken(tokenNameField.getText(), accessTokenValue);
  }
}
