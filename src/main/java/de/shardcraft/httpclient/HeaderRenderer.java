package de.shardcraft.httpclient;

import java.util.List;
import java.util.Map;

class HeaderRenderer {

  public String render(Map<String, List<String>> header) {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, List<String>> headerEntry : header.entrySet()) {
      result
          .append(headerEntry.getKey())
          .append(": ")
          .append(String.join(", ", headerEntry.getValue()))
          .append(System.lineSeparator());
    }
    return result.toString();
  }
}
