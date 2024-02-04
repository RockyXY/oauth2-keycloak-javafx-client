package de.shardcraft.token;

import com.nimbusds.oauth2.sdk.token.Token;
import java.util.Base64;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TokensController {

  private final TokenManager tokenManager;
  @FXML private ComboBox<Token> tokenSelectionField;
  @FXML private TextArea tokenDisplay;

  public TokensController(TokenManager tokenManager) {
    this.tokenManager = tokenManager;
  }

  @FXML
  public void initialize() {
    tokenSelectionField.itemsProperty().bindBidirectional(tokenManager.getTokenList());
    tokenSelectionField
        .valueProperty()
        .addListener((observable, oldValue, newValue) -> tokenDisplay.setText(newValue.getValue()));
  }

  @FXML
  public void decodeSelectedToken(ActionEvent actionEvent) {
    Token selectedToken = tokenSelectionField.getValue();
    if (selectedToken != null) {
      String[] encodedTokenParts = selectedToken.getValue().split("\\.");
      Base64.Decoder decoder = Base64.getUrlDecoder();

      String header = new String(decoder.decode(encodedTokenParts[0]));
      String payload = new String(decoder.decode(encodedTokenParts[1]));

      try {
        JSONObject headerJsonObject = new JSONObject(header);
        JSONObject payloadJsonObject = new JSONObject(payload);
        tokenDisplay.setText(
            headerJsonObject.toString(2) + System.lineSeparator() + payloadJsonObject.toString(2));
      } catch (JSONException ex) {
        tokenDisplay.setText(header + System.lineSeparator() + payload);
      }
    }
  }
}
