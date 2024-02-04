package de.shardcraft.action;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.util.StringUtils;

public class RequestRenderer {

  public String render(HTTPRequest request) {
    StringBuilder result =
        new StringBuilder("---------- Request ----------" + System.lineSeparator());
    result
        .append(request.getMethod())
        .append(" ")
        .append(request.getURI().toASCIIString())
        .append(System.lineSeparator());
    if (StringUtils.isNotBlank(request.getAuthorization())) {
      result
          .append("Authorization-Header: ")
          .append(request.getAuthorization())
          .append(System.lineSeparator());
    }
    if (StringUtils.isNotBlank(request.getBody())) {
      result
          .append("Body: ")
          .append(System.lineSeparator())
          .append(request.getBody())
          .append(System.lineSeparator());
    }
    result.append("-----------------------------").append(System.lineSeparator());
    return result.toString();
  }
}
