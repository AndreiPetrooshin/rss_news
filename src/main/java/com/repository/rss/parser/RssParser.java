package com.repository.rss.parser;


import com.repository.rss.parser.exception.RssParserException;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

    @Component
    public class RssParser {

        public List parseNews(String rssUrl) throws RssParserException {
            try {
                URL url = new URL(rssUrl);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(httpConnection));
                return feed.getEntries();
            } catch (IOException | FeedException e) {
                throw new RssParserException(e);
            }
        }

        public SyndFeed parseRssHolder(String rssUrl) throws RssParserException {
            try {
                URL url = new URL(rssUrl);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                SyndFeedInput input = new SyndFeedInput();
                return input.build(new XmlReader(httpConnection));
            } catch (IOException | FeedException e) {
                throw new RssParserException(e);
            }
        }
    }
