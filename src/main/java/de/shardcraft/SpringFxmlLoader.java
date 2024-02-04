package de.shardcraft;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringFxmlLoader {

  private static final ApplicationContext applicationContext =
      new AnnotationConfigApplicationContext(Application.class);

  public Parent load(String name) {
    FXMLLoader loader = new FXMLLoader(Application.class.getResource("/" + name + ".fxml"));
    loader.setControllerFactory(applicationContext::getBean);
    try {
      return loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
