package ru.ncedu.magpie.connect;

import com.google.gson.annotations.SerializedName;

/**
 * Class for getting access token from VK API 
 * @author keers
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

