package de.shardcraft.requests;

import de.shardcraft.SpringFxmlLoader;
import de.shardcraft.action.RequestActionCell;
import de.shardcraft.action.RequestActionType;
import de.shardcraft.action.introspection.TokenIntrospectionRequestActionType;
import de.shardcraft.action.tokenrequest.TokenRequestActionType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class RequestsController {

  @FXML public ComboBox<RequestActionType> requestComboBox;
  @FXML public VBox dynamicFormContainer;

  @FXML
  public void initialize() {
    requestComboBox.setCellFactory(param -> new RequestActionCell());
    requestComboBox.setButtonCell(new RequestActionCell());
    requestComboBox.setItems(
        FXCollections.observableArrayList(
            new TokenIntrospectionRequestActionType(), new TokenRequestActionType()));
    requestComboBox
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              dynamicFormContainer.getChildren().clear();
              dynamicFormContainer.getChildren().add(loadFXML(newValue.getFxmlName()));
            });
  }

  private Parent loadFXML(String fxml) {
    return SpringFxmlLoader.getInstance().load(fxml);
  }
}
