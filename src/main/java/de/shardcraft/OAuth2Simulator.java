package de.shardcraft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OAuth2Simulator extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    Scene scene = new Scene(loadFXML("main"));
    stage.setScene(scene);
    stage.show();
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(OAuth2Simulator.class.getResource("/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
}
