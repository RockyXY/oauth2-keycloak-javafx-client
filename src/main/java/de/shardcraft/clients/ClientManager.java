package de.shardcraft.clients;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.springframework.stereotype.Component;

@Component
public class ClientManager {

  private final ListProperty<ClientEntry> clientEntries =
      new SimpleListProperty<>(FXCollections.observableArrayList(ClientEntry.newClient));

  public ListProperty<ClientEntry> getClientList() {
    return clientEntries;
  }

  public void save(ClientEntry entry) {
    if (!ClientEntry.newClient.getClientId().equals(entry.getClientId())) {
      clientEntries.removeIf(client -> client.getClientId().equals(entry.getClientId()));
      clientEntries.add(entry);
    }
  }
}
