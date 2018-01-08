package com.repository.rss.service.impl;

import com.repository.rss.domain.News;
import com.repository.rss.repository.NewsDao;
import com.repository.rss.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> getAll() {
        return newsDao.findAll();
    }

    @Override
    public News getById(Integer id) {
        return newsDao.findOne(id);
    }

    @Override
    public void save(News news) {
        newsDao.save(news);
    }

    @Override
    public void delete(News news) {
        newsDao.delete(news);
    }

    @Override
    public void deleteById(Integer id) {
        newsDao.delete(id);
    }
}
