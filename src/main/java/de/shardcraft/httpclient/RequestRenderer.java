package de.shardcraft.httpclient;

public class RequestRenderer {

  public String render(CustomHttpRequest request) {
    StringBuilder result = new StringBuilder("Request" + System.lineSeparator());
    result.append(request.getRequest().method()).append(System.lineSeparator());
    result.append(request.getRequest().uri().toASCIIString()).append(System.lineSeparator());
    if (request.hasHeader()) {
      result.append(new HeaderRenderer().render(request.getHeader()));
    }
    if (request.hasBody()) {
      result.append(request.getBody()).append(System.lineSeparator());
    }
    return result.toString();
  }
}
