package com.repository.rss.repository;

import com.repository.rss.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDao extends JpaRepository<News, Integer> {
}
