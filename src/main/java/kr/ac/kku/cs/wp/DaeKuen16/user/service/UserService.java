package kr.ac.kku.cs.wp.DaeKuen16.user.service;

import java.util.List;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;

public interface UserService {

    public User getUserById(String userId);
    public User getUser(User user);
    public User updateUser(User user);
    public void deleteUser(User user);
    public void createUser(User user);
    public List<User> getUsers(User user);

}