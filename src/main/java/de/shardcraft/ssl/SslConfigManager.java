package de.shardcraft.ssl;

import org.springframework.stereotype.Component;

@Component
public class SslConfigManager {

  private final SslConfiguration sslConfig = new SslConfiguration();

  public SslConfiguration getSslConfig() {
    return sslConfig;
  }
}
