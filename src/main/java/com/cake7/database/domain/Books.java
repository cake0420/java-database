package com.cake7.database.domain;

public class Books {
    private final byte[] id;
    private final String title;
    private final String author;

    public Books(byte[] id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public byte[] getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
}
