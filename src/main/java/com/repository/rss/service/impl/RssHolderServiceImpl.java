package com.repository.rss.service.impl;

import com.repository.rss.domain.RssHolder;
import com.repository.rss.repository.RssHolderDao;
import com.repository.rss.service.RssHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssHolderServiceImpl implements RssHolderService {


    @Autowired
    private RssHolderDao holderDao;

    @Override
    public List<RssHolder> getAll() {
        return holderDao.findAll();
    }

    @Override
    public RssHolder getById(Integer id) {
        return holderDao.findOne(id);
    }


    @Override
    public void save(RssHolder rssHolder) {
        holderDao.save(rssHolder);
    }

    @Override
    public void delete(RssHolder rssHolder) {
        holderDao.delete(rssHolder);
    }

    @Override
    public void deleteById(Integer id) {
        holderDao.delete(id);
    }

    public RssHolder findByLink(String link) {
        return holderDao.findByLink(link);
    }
}
