package model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private Map<String, String> fileAccessRights;
    private boolean right;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.fileAccessRights = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getFileAccessRights() {
        return fileAccessRights;
    }

    public void setFileAccessRights(Map<String, String> fileAccessRights) {
        this.fileAccessRights = fileAccessRights;
    }

    public void addFileAccessRight(String fileName, String accessRight) {
        this.fileAccessRights.put(fileName, accessRight);
    }

    public String getFileAccessRight(String fileName) {
        return this.fileAccessRights.get(fileName);
    }

    public void grantGrantRight(String username) {
        this.fileAccessRights.put("GRANT", username);
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean hasGrantRight(String username) {
        return this.fileAccessRights.containsValue("GRANT") && this.fileAccessRights.get("GRANT").equals(username);
    }
    @Override
    public String toString() {
        return username;
    }
}