package ir.moke.dandelion.model;

import java.util.Objects;

public class Credential {
    private String auth ;
    private String accessKey ;
    private String deviceId ;

    public Credential(String auth, String accessKey, String deviceId) {
        this.auth = auth;
        this.accessKey = accessKey;
        this.deviceId = deviceId;
    }

    public Credential() {
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return Objects.equals(auth, that.auth) &&
                Objects.equals(accessKey, that.accessKey) &&
                Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auth, accessKey, deviceId);
    }

    @Override
    public String toString() {
        return "Credential{" +
                "auth='" + auth + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
