package com.cake7.database.domain;

import java.util.UUID;

public class Books {
    private final UUID id;
    private final String title;
    private final String author;

    public Books(String title, String author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
    }

    public UUID getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
}
