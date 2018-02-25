package Helpers;

import javax.ws.rs.core.HttpHeaders;

public interface tokenDecrypter {
    String getId(HttpHeaders httpHeaders);
}
