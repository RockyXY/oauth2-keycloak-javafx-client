package de.shardcraft.token;

import com.nimbusds.oauth2.sdk.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntry {

  private String name;
  private Token token;
}
