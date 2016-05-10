package org.mytraining.dao;


import org.mytraining.model.User;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserDAO {
    public void addUser(User user);
    public void updateUser(User user);
    public List<User> listUsers();
    public List<User> listUsersByName(String name);
    public User getUserById(int id);
    public void removeUser(int id);
}
