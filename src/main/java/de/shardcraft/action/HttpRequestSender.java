package de.shardcraft.action;

import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.util.tls.TLSUtils;
import com.nimbusds.oauth2.sdk.util.tls.TLSVersion;
import de.shardcraft.console.ConsoleManager;
import de.shardcraft.ssl.SslConfigManager;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLSocketFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestSender {

  private final SslConfigManager sslConfigManager;
  private final ConsoleManager consoleManager;

  public HttpRequestSender(SslConfigManager sslConfigManager, ConsoleManager consoleManager) {
    this.sslConfigManager = sslConfigManager;
    this.consoleManager = consoleManager;
  }

  public HTTPResponse send(HTTPRequest request) {
    try {
      consoleManager.addText(new RequestRenderer().render(request));
      if (request.getURI().getScheme().equals("https")) {
        request.setSSLSocketFactory(createSslSocketFactory());
      }
      return request.send();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private SSLSocketFactory createSslSocketFactory() throws Exception {
    File trustStoreFile = new File(sslConfigManager.getSslConfig().getPathToTruststore().get());
    String trustStorePassword = sslConfigManager.getSslConfig().getTrustStorePassword().get();

    // default type is "pkcs12"
    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
    trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword.toCharArray());

    // TLS 1.3 has been standard since 2018 and is recommended.
    return TLSUtils.createSSLSocketFactory(trustStore, TLSVersion.TLS_1_3);
  }
}
