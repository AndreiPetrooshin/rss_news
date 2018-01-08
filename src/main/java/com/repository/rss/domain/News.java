package com.repository.rss.domain;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "news")
public class News implements Identified {


    private static final long serialVersionUID = 1L;

    private int id;
    private Date date;
    private String title;
    private String description;
    private String link;
    private RssHolder rssHolder;
    private int views;

    public News(int id, Date date, String title,
                String description, String link, RssHolder rssHolder, int views) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.link = link;
        this.rssHolder = rssHolder;
        this.views = views;
    }

    public News() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "pub_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rss_holder_id")
    public RssHolder getRssHolder() {
        return rssHolder;
    }


    public void setRssHolder(RssHolder rssHolder) {
        this.rssHolder = rssHolder;
    }


}
