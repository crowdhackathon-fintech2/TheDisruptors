package com.ngbfintech2.api.dao;

public class User {
    private final long id;
    private final String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return name;
    }
}
