package com.example.madforumapp;

public class Answer {
    private int id;
    private String description;
    private String author;

    public Answer(){

    }

    public Answer(int id, String description, String author) {
        this.id = id;
        this.description = description;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
