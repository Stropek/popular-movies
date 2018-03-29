package com.example.pscurzytek.popularmovies.models;

public class Trailer implements ObjectWithId {
    private final String id;
    private final String key;
    private final String name;
    private final String site;
    private final int size;
    private final TrailerType type;

    public Trailer(String id, String key, String name, String site, int size, TrailerType type) {
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

    public int getSize() {
        return size;
    }

    public TrailerType getType() {
        return type;
    }

    @Override
    public String getIdAsString() {
        return id;
    }
}

