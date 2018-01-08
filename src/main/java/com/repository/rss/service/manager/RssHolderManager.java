package com.repository.rss.service.manager;

import com.repository.rss.domain.RssHolder;
import com.repository.rss.domain.User;
import com.repository.rss.parser.RssParser;
import com.repository.rss.parser.exception.RssParserException;
import com.repository.rss.service.impl.RssHolderServiceImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class RssHolderManager {

    @Autowired
    private RssParser rssParser;

    @Autowired
    private RssHolderServiceImpl service;


    public RssHolder createRssHolder(String url) throws RssParserException {
        SyndFeed feed = rssParser.parseRssHolder(url);
        String category = feed.getTitle();
        String link = url;
        int beginIndex = link.indexOf("://") + 3;
        String author = link.substring(beginIndex);
        HashSet<User> users = new HashSet<>();
        RssHolder rssHolder = new RssHolder(0, author, category, link, users);
        RssHolder holder = service.findByLink(link);
        if (holder == null) {
            service.save(rssHolder);
            return service.findByLink(link);
        } else {
            return holder;
        }
    }

}
