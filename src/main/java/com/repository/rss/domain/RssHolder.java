package com.repository.rss.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "rss_holder")
public class RssHolder implements Identified {

    private static final long serialVersionUID = 2L;

    private int id;
    private String creator;
    private String category;
    private String link;
    private Set<User> users = new HashSet<>();

    public RssHolder(int id, String creator, String category, String link, Set<User> users) {
        this.id = id;
        this.creator = creator;
        this.category = category;
        this.link = link;
        this.users = users;
    }

    public RssHolder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_holder_m2m", joinColumns = {
            @JoinColumn(name = "rss_holder_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RssHolder rssHolder = (RssHolder) o;

        if (id != rssHolder.id) {
            return false;
        }
        if (creator != null ? !creator.equals(rssHolder.creator) : rssHolder.creator != null) {
            return false;
        }
        if (category != null ? !category.equals(rssHolder.category) : rssHolder.category != null) {
            return false;
        }
        return link != null ? link.equals(rssHolder.link) : rssHolder.link == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
