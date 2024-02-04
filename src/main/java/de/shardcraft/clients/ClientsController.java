package de.shardcraft.clients;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClientsController {

  private final ClientManager clientManager;
  @FXML public ListView<ClientEntry> clientListView;
  @FXML public TextField clientIdField;
  @FXML public TextField clientSecretField;
  @FXML public VBox formFieldContainer;

  public ClientsController(ClientManager clientManager) {
    this.clientManager = clientManager;
  }

  @FXML
  public void initialize() {
    clientListView.setCellFactory(param -> new ClientEntryCell());
    clientListView
        .itemsProperty()
        .bindBidirectional(
            new SimpleListProperty<>(
                new SortedList<>(clientManager.getClientList(), new ClientEntryComparator())));
    clientListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    clientListView.getSelectionModel().selectedItemProperty().addListener(this::onSelectionChanged);
    formFieldContainer.setVisible(false);
  }

  private void onSelectionChanged(
      ObservableValue<? extends ClientEntry> observable,
      ClientEntry oldValue,
      ClientEntry newValue) {
    formFieldContainer.setVisible(true);
    if (newValue != null && !newValue.getClientId().equals(ClientEntry.newClient.getClientId())) {
      clientIdField.setText(newValue.getClientId());
      clientSecretField.setText(newValue.getSecret());
    } else {
      clientIdField.setText("");
      clientSecretField.setText("");
    }
  }

  @FXML
  public void onSave() {
    String clientId = clientIdField.getText();
    String secret = clientSecretField.getText();

    if (StringUtils.hasText(clientId)) {
      ClientEntry entry = new ClientEntry();
      entry.setClientId(clientId);
      entry.setSecret(secret);
      clientManager.save(entry);
      deselectAll();
    }
  }

  private void deselectAll() {
    formFieldContainer.setVisible(false);
    clientListView.getSelectionModel().clearSelection();
  }
}
