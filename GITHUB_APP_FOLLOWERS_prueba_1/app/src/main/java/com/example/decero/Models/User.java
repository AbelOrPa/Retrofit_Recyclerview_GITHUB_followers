package com.example.decero.Models;

public class User {

    private String login;
    private int followers;
    private int public_repos;
    private String avatar_url;


    public String getLogin() {
        return login;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
