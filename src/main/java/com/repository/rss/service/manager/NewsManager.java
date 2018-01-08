package com.repository.rss.service.manager;

import com.repository.rss.domain.News;
import com.repository.rss.domain.RssHolder;
import com.repository.rss.parser.RssParser;
import com.repository.rss.parser.exception.RssParserException;
import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsManager {

    @Autowired
    private RssParser rssParser;

    public List<News> getListNews(List<RssHolder> holders) throws RssParserException {
        List<News> allNews = new ArrayList<>();
        for (RssHolder holder : holders) {
            try {
                List news = rssParser.parseNews(holder.getLink());
                allNews.addAll(news);
                allNews = sortNews(allNews, holder);
            } catch (Exception e) {
                throw new RssParserException(e);
            }
        }
        return allNews;
    }

    private List<News> sortNews(List listNews, RssHolder rssHolder) {
        List<News> news = new ArrayList<>();
        for (Object listNew : listNews) {
            SyndEntry entry = (SyndEntry) listNew;
            news.add(createNews(entry, rssHolder));
        }
        news.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return news;
    }

    private News createNews(SyndEntry entry, RssHolder rssHolder) {
        return new News(0, entry.getPublishedDate(), entry.getTitle(),
                entry.getDescription().getValue(), entry.getLink(), rssHolder, 0);
    }
}
