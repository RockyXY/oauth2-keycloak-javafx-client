package de.shardcraft.token;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenManager {

  private final ListProperty<TokenEntry> tokens =
      new SimpleListProperty<>(FXCollections.observableArrayList());

  public ListProperty<TokenEntry> getTokenList() {
    return tokens;
  }

  public void addToken(TokenEntry token) {
    tokens.removeIf(t -> token.getName().equals(t.getName()));
    tokens.add(token);
  }
}
