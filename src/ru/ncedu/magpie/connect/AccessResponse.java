package ru.ncedu.magpie.connect;

import com.google.gson.annotations.SerializedName;

/**
 * User: sergeikirsanov
 * Date: 3/5/12
 * Time: 10:04 PM
 */
public class AccessResponse {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private String expiresIn;

    @SerializedName("user_id")
    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpires_in() {
        return expiresIn;
    }

    public void setExpires_in(String expires_in) {
        this.expiresIn = expires_in;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

