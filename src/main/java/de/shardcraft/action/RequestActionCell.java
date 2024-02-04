package de.shardcraft.action;

import javafx.scene.control.ListCell;

public class RequestActionCell extends ListCell<RequestActionType> {
  @Override
  public void updateItem(RequestActionType item, boolean empty) {
    super.updateItem(item, empty);
    if (item != null) {
      setText(item.getName());
    } else {
      setText(null);
    }
  }
}
