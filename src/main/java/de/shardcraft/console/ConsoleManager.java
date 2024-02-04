package de.shardcraft.console;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConsoleManager {

  private final StringProperty textProperty = new SimpleStringProperty("");

  public StringProperty getTextProperty() {
    return textProperty;
  }

  public void addText(String text) {
    textProperty.setValue(textProperty.getValue() + text);
  }

  public void clear() {
    textProperty.setValue("");
  }
}
