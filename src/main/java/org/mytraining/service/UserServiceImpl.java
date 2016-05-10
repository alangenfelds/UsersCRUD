package org.mytraining.service;

import org.mytraining.dao.UserDAO;
import org.mytraining.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return this.userDAO.listUsers();
    }

    @Override
    @Transactional
    public List<User> listUsersByName(String name) {
        return this.userDAO.listUsersByName(name);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDAO.removeUser(id);
    }
}
