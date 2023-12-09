package com.example.finalprojectwj;

public class BBCItem {
    private String title;
    private String description;
    private String link;

    public BBCItem(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getLink() {
        return link;
    }
}
