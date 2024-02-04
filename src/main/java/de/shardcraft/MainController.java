package de.shardcraft;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class MainController {

  @FXML
  public void closeApplication() {
    System.exit(0);
  }
}
