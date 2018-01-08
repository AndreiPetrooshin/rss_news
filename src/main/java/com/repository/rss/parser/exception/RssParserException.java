package com.repository.rss.parser.exception;

public class RssParserException extends Exception {

    public RssParserException(Exception e) {
    }

    public RssParserException(String message) {
        super(message);
    }

    public RssParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
