package com.example.pscurzytek.popularmovies.models;

public class Trailer {
    private final String id;
    private final String key;
    private final String name;
    private final String site;
    private final Integer size;
    private final TrailerType type;

    public Trailer(String id, String key, String name, String site, Integer size, TrailerType type) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public Integer getSize() {
        return size;
    }

    public TrailerType getType() {
        return type;
    }
}

