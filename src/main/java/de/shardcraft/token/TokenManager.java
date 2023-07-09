package de.shardcraft.token;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.Optional;

public class TokenManager {

  private static final TokenManager instance = new TokenManager();

  public static TokenManager getInstance() {
    return instance;
  }

  private final ListProperty<Token> tokens =
      new SimpleListProperty<>(FXCollections.observableArrayList());

  public ListProperty<Token> getTokenList() {
    return tokens;
  }

  public void addToken(String name, String value) {
    tokens.removeIf(token -> token.getName().equals(name));
    tokens.add(new Token(name, value));
  }

  public Optional<Token> findToken(String name) {
    return tokens.stream().filter(token -> token.getName().equals(name)).findFirst();
  }
}
