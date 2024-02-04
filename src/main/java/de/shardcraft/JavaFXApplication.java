package de.shardcraft;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication extends javafx.application.Application {

  private static final SpringFxmlLoader loader = new SpringFxmlLoader();

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(loader.load("main"));
    stage.setScene(scene);
    stage.show();
  }
}
