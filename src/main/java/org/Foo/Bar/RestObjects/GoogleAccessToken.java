package org.Foo.Bar.RestObjects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoogleAccessToken {
  private String access_token;
  private Integer expires_in;
  private String scope;
  private String token_type;
  private String id_token;
}
