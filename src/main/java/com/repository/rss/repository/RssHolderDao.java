package com.repository.rss.repository;

import com.repository.rss.domain.RssHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RssHolderDao extends JpaRepository<RssHolder, Integer> {
    RssHolder findByLink(String link);
}
