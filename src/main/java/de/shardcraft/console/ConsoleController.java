package de.shardcraft.console;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Component;

@Component
public class ConsoleController {
  private final ConsoleManager consoleManager;
  @FXML public TextArea consoleTextArea;

  public ConsoleController(ConsoleManager consoleManager) {
    this.consoleManager = consoleManager;
  }

  @FXML
  public void initialize() {
    consoleTextArea.textProperty().bind(consoleManager.getTextProperty());
  }

  @FXML
  public void clearConsole(ActionEvent actionEvent) {
    //    ConsoleManager.getInstance().clear();
    consoleManager.clear();
  }
}
