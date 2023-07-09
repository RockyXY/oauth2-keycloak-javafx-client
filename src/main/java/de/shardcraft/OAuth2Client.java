package de.shardcraft;

import de.shardcraft.httpclient.HttpRequestSender;
import lombok.Data;

import java.net.http.HttpResponse;

// keycloak.client-id=jakarta-school
// keycloak.client-secret=197bc3b4-64b0-452f-9bdb-fcaea0988e90
// keycloak.scope=openid, profile
// keycloak.authorization-grant-type=password
//
// keycloak.authorization-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/auth
// keycloak.user-info-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/userinfo
// keycloak.token-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/token
// keycloak.logout=http://localhost:8080/auth/realms/education/protocol/openid-connect/logout
// keycloak.jwk-set-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/certs
// keycloak.certs-id=vdaec4Br3ZnRFtZN-pimK9v1eGd3gL2MHu8rQ6M5SiE

public class OAuth2Client {

  private final HttpRequestSender requestSender = new HttpRequestSender();

  private final String keycloakUri;
  private final String realm;

  public OAuth2Client(String keycloakUri, String realm) {
    this.keycloakUri = keycloakUri;
    this.realm = realm;
  }

  @Data
  private static class AuthorizationCodeFlowBody {
    private String response_type = "code";
    private String grant_type = "authorization_code";
    private String client_id;
    private String client_secret;
  }

  @Data
  private static class ClientCredentialsFlowBody {
    private String client_id;
    private String client_secret;
    //    private String scope = "code";
    private String grant_type = "client_credentials";
  }

  public HttpResponse<String> wellKnown() {
    return requestSender.get(
        keycloakUri + "/realms/" + realm + "/.well-known/openid-configuration");
  }

  // TODO: authorization code flow with user login
  //  public HttpResponse<String> authorize(ClientCredentials clientCredentials) {
  //    ClientCredentialsFlowBody body = new ClientCredentialsFlowBody();
  //    body.setClient_id(clientCredentials.getClientId());
  //    body.setClient_secret(clientCredentials.getSecret());
  //    return requestSender.postFormUrlEncoded(
  //        keycloakUri + "/realms/" + realm + "/protocol/openid-connect/auth", body);
  //  }

  /** Needs Service Account "on" in keycloak */
  public HttpResponse<String> getAccessToken(ClientCredentials clientCredentials) {
    ClientCredentialsFlowBody body = new ClientCredentialsFlowBody();
    body.setClient_id(clientCredentials.getClientId());
    body.setClient_secret(clientCredentials.getSecret());
    return requestSender.postFormUrlEncoded(
        keycloakUri + "/realms/" + realm + "/protocol/openid-connect/token", body);
  }

  public HttpResponse<String> getJwks() {
    return requestSender.get(keycloakUri + "/realms/" + realm + "/protocol/openid-connect/certs");
  }
}
