package de.shardcraft.ssl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SslConfigManager {

  private final SslConfiguration sslConfig = new SslConfiguration();

  public SslConfiguration getSslConfig() {
    return sslConfig;
  }
}
