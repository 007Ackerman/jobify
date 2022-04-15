package com.example.jobify.model;

public class UserModel {

    String uid,email,name,password;
    long timestamp,phone;

    public UserModel() {
    }

    public UserModel(String uid, String email, String name, String password, long timestamp, long phone) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.timestamp = timestamp;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
