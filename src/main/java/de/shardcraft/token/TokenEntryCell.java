package de.shardcraft.token;

public class TokenEntryCell extends javafx.scene.control.ListCell<TokenEntry> {

  @Override
  public void updateItem(TokenEntry item, boolean empty) {
    super.updateItem(item, empty);
    if (item != null) {
      setText(item.getName());
    } else {
      setText(null);
    }
  }
}
