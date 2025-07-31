package deser_java_lab.actions.common.serializable;

import java.io.Serializable;

public class AccessTokenUser implements Serializable {
    private final String username;
    private final String accessToken;
    public AccessTokenUser(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public String getUserName() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
