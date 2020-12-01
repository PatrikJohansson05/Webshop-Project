package se.lexicon.patrik.Webshopproject.model;

import java.util.Objects;

public class WebUser {
    private Integer userId;
    private String name;
    private String email;
    private String address;

    public WebUser(Integer userId, String name, String email, String address){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public WebUser(){
    }

    public Integer getUserId() {
        return userId;
    }


    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebUser user = (WebUser) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}