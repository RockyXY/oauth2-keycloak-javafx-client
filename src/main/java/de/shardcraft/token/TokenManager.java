package de.shardcraft.token;

import com.nimbusds.oauth2.sdk.token.Token;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

  private final ListProperty<Token> tokens =
      new SimpleListProperty<>(FXCollections.observableArrayList());

  public ListProperty<Token> getTokenList() {
    return tokens;
  }

  public void addToken(Token token) {
    tokens.removeIf(t -> token.getValue().equals(t.getValue()));
    tokens.add(token);
  }
}
