package de.shardcraft;

import de.shardcraft.token.Token;
import de.shardcraft.token.TokenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

public class TokensController {

  @FXML private ComboBox<Token> tokenSelectionField;
  @FXML private TextArea tokenDisplay;

  @FXML
  public void initialize() {
    tokenSelectionField
        .itemsProperty()
        .bindBidirectional(TokenManager.getInstance().getTokenList());
    tokenSelectionField.setConverter(
        new StringConverter<>() {
          @Override
          public String toString(Token token) {
            return token != null ? token.getName() : "";
          }

          @Override
          public Token fromString(String name) {
            return TokenManager.getInstance().findToken(name).orElse(null);
          }
        });
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
