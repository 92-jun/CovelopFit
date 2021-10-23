package com.covelopfit.autotrading.domain;

public class Member {
    private String name;
    private UpbitKey key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpbitKey getKey() {
        return key;
    }

    public void setKey(UpbitKey key) {
        this.key = key;
    }
}
