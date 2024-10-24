package kr.ac.kku.cs.wp.DaeKuen16.user.dao;

import static org.junit.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kr.ac.kku.cs.wp.DaeKuen16.user.mapper.UserMapper;
import kr.ac.kku.cs.wp.DaeKuen16.support.sql.MybatisUtil;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.Role;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRole;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRoleId;
import kr.ac.kku.cs.wp.DaeKuen16.user.dao.*;

public class UserDAOMybatisImplTest {

    private static final Logger logger = LogManager.getLogger(UserDAOMybatisImplTest.class);

    @Test
    public void testGetUserById() {
        UserDAO dao = new UserDAOMybatisImpl();
        User user = dao.getUserById("kku_1001");
        assertNotNull(user);
        List<UserRole> urList = user.getUserRoles();

        for (UserRole userRole : urList) {
            User urUser = userRole.getUser();
            Role role = userRole.getRole();
            logger.debug("user id {}, user name {}, role name {}", 
                urUser.getId(),
                urUser.getName(),
                role.getRole());
        }
    }

    @Test
    public void testGetUsers() {
        UserDAO userDao = new UserDAOMybatisImpl();
        List<User> users = userDao.getUsers(null);
        assertNotNull(users);

        for (User user : users) {
            List<UserRole> urList = user.getUserRoles();
            for (UserRole userRole : urList) {
                User urUser = userRole.getUser();
                Role role = userRole.getRole();
                logger.debug("user id {}, user name {}, role name {}", 
                    urUser.getId(),
                    urUser.getName(),
                    role.getRole());
            }
        }
    }
}
