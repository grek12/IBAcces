package model;

import java.util.HashMap;
import java.util.Map;

public class UserManagement {
    private Map<String, User> users;
    private Map<String, AbstractFile> files;
    private User currentUser;

    public UserManagement() {
        this.users = new HashMap<>();
        this.files = new HashMap<>();
    }

    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
    }

    public void addFile(AbstractFile file) {
        this.files.put(file.getFileName(), file);
    }

    public boolean authorize(String username, String password) {
        User user = this.users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

//    public void grantAccessRight(String fileName, String username, String accessRight) {
//        if (this.currentUser != null && this.currentUser.getUsername().equals("admin")) {
//            User user = this.users.get(username);
//            if (user != null) {
//                user.addFileAccessRight(fileName, accessRight);
//            }
//        }
//    }

public void grantAccessRight(String fileName, String username, String accessRight) {
        if (this.currentUser != null && (this.currentUser.getUsername().equals("admin") || this.currentUser.isRight())) {
            User user = this.users.get(username);
            if (user != null) {
                user.addFileAccessRight(fileName, accessRight);
            }
        }
    }
    public void grantRight(String username, Boolean Right) {
        if (this.currentUser != null && (this.currentUser.getUsername().equals("admin") || this.currentUser.isRight())) {
            User user = this.users.get(username);
            if (user != null) {
                user.setRight(true);
            }
        }
    }
    public Map<String, String> getUserAccessRights(String username) {
        User user = this.users.get(username);
        if (user != null) {
            return user.getFileAccessRights();
        }
        return null;
    }
    public Map<String, User> getAllUsers() {
        if  (this.currentUser != null && (this.currentUser.getUsername().equals("admin") || this.currentUser.isRight())) {
            return this.users;
        }
        return null;
    }

    public Map<String, AbstractFile> getAllFiles() {
        if  (this.currentUser != null && (this.currentUser.getUsername().equals("admin") || this.currentUser.isRight())) {
            return this.files;
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}