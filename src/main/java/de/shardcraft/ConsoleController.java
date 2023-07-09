package de.shardcraft;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ConsoleController {
  @FXML public TextArea consoleTextArea;

  @FXML
  public void initialize() {
    consoleTextArea.textProperty().bind(ConsoleManager.getInstance().getTextProperty());
  }
}
