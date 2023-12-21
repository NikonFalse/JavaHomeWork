package ru.sadykoff.skillbox.web.dto;

public class Book {
    private Integer id;
    private String writer;
    private String heading;
    private Integer scale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return writer;
    }

    public void setAuthor(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return heading;
    }

    public void setTitle(String title) {
        this.heading = title;
    }

    public Integer getSize() {
        return scale;
    }

    public void setSize(Integer size) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", heading='" + heading + '\'' +
                ", scale=" + scale +
                '}';
    }
}
