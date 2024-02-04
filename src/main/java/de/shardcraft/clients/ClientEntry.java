package de.shardcraft.clients;

import lombok.Data;

@Data
public class ClientEntry {

  public static final ClientEntry newClient =
      new ClientEntry() {
        @Override
        public String getClientId() {
          return "+ New Client";
        }
      };

  private String clientId;
  private String secret;
}
