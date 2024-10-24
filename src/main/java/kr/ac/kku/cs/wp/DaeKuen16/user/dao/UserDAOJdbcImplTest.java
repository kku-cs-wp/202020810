package kr.ac.kku.cs.wp.DaeKuen16.user.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kr.ac.kku.cs.wp.DaeKuen16.user.entity.Role;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRole;

public class UserDAOJdbcImplTest {

    private static final Logger logger = LogManager.getLogger(UserDAOJdbcImplTest.class);

    @Test
    public void testGetUserById() {
        UserDAO userDao = new UserDAOJdbcImpl();
        String id = "kku_1001";
        User user = userDao.getUserById(id);
        assertNotNull("User should not be null", user);  // 추가적인 에러 메시지
        
        List<UserRole> urList = user.getUserRoles();
        assertNotNull("User roles should not be null", urList);  // 유저의 역할 리스트 확인

        for (UserRole userRole : urList) {
            User urUser = userRole.getUser();
            Role role = userRole.getRole();

            logger.debug(String.format("user id: %s, user name: %s, role name: %s", 
                urUser.getId(),
                urUser.getName(),
                role.getRole()));
        }
    }

    @Test
    public void testGetUsers() {
        UserDAO userDao = new UserDAOJdbcImpl();
        List<User> users = userDao.getUsers(null);
        assertNotNull("Users list should not be null", users);

        for (User user : users) {
            List<UserRole> urList = user.getUserRoles();
            assertNotNull("User roles should not be null", urList);  // 각 사용자의 역할 리스트 확인

            for (UserRole userRole : urList) {
                User urUser = userRole.getUser();
                Role role = userRole.getRole();

                logger.debug(String.format("user id: %s, user name: %s, role name: %s", 
                    urUser.getId(),
                    urUser.getName(),
                    role.getRole()));
            }
        }
    }
}
