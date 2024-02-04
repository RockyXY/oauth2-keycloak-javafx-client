package de.shardcraft.action.tokenrequest;

import de.shardcraft.action.RequestActionType;

public class TokenRequestActionType implements RequestActionType {
  @Override
  public String getName() {
    return "Token Request";
  }

  @Override
  public String getFxmlName() {
    return "token-request";
  }
}
