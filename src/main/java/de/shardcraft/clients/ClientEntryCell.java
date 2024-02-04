package de.shardcraft.clients;

public class ClientEntryCell extends javafx.scene.control.ListCell<ClientEntry> {

  @Override
  public void updateItem(ClientEntry item, boolean empty) {
    super.updateItem(item, empty);
    if (item != null) {
      setText(item.getClientId());
    } else {
      setText(null);
    }
  }
}
