package no.helsebiblioteket.admin.service;

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.User;

public interface AdminService extends Serializable {
    public void setUserDao(UserDao userDao);
    public User findUserByUsername(User user);
    public List<User> getUserList();
    public void createUser(User user);
}
