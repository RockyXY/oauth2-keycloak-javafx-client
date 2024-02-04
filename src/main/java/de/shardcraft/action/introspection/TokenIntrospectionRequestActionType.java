package de.shardcraft.action.introspection;

import de.shardcraft.action.RequestActionType;

public class TokenIntrospectionRequestActionType implements RequestActionType {
  @Override
  public String getName() {
    return "Token Introspection";
  }

  @Override
  public String getFxmlName() {
    return "token-introspection";
  }
}
