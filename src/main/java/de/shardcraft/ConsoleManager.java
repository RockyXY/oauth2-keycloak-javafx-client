package de.shardcraft;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConsoleManager {

  private static final ConsoleManager instance = new ConsoleManager();

  public static ConsoleManager getInstance() {
    return instance;
  }

  private final StringProperty textProperty = new SimpleStringProperty();

  public StringProperty getTextProperty() {
    return textProperty;
  }

  public void addText(String text) {
    textProperty.setValue(textProperty.getValue() + "\n" + text);
  }
}
