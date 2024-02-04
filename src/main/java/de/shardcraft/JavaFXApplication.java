package de.shardcraft;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication extends javafx.application.Application {

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(SpringFxmlLoader.getInstance().load("main"));
    stage.setScene(scene);
    stage.show();
  }
}
