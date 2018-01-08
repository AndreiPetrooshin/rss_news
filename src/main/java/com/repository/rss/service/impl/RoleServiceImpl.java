package com.repository.rss.service.impl;

import com.repository.rss.domain.Role;
import com.repository.rss.repository.RoleDao;
import com.repository.rss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleDao.findOne(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public void deleteById(Integer id) {
        roleDao.delete(id);
    }
}
