package kr.ac.kku.cs.wp.DaeKuen16.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import kr.ac.kku.cs.wp.DaeKuen16.user.dao.*;

import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;

public class UserServiceImple implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImple.class);

    private UserDAO dao = new UserDAOHiberanteImpl();
   

    @Override
    public List<User> getUsers(User user) {
        // TODO Auto-generated method stub
        return dao.getUsers(user);
    }
    @Override
    public User getUserById(String userId) {
    	return dao.getUserById(userId);
    }
    @Override
    public User getUser(User user) {
    	return dao.getUser(user);
    }
    @Override
    public User updateUser(User user) {
    	return dao.updateUser(user);
    }
    @Override
    public void deleteUser(User user) {
    }
    @Override
    public void createUser(User user) {
    }
}