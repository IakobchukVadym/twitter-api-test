package com.twitter.request;

public enum ScreenName {

    ARSENAL("Arsenal"),
    GUNNERSAURUS("Gunnersaurus");

    private String name;

    ScreenName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
