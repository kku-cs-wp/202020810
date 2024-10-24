package kr.ac.kku.cs.wp.DaeKuen16.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;

public class UserServiceImpleTest {

    private static final Logger logger = LogManager.getLogger(UserServiceImpleTest.class);

    private UserServiceImple userService;

    @Before
    public void setUp() {
        // 테스트를 실행하기 전에 호출되는 메서드
        userService = new UserServiceImple(); // UserServiceImpl 인스턴스 생성
    }

    @Test
    public void testGetUsers() {
        // User 목록을 가져오는 메서드 테스트
        User user = null; // 모든 사용자를 조회할 때는 null을 전달
        List<User> users = userService.getUsers(user);
        
        assertNotNull(users); // 목록이 null이 아니어야 함
        logger.debug("Number of users: " + users.size());
        assertTrue(users.size() > 0); // 최소 1명 이상의 사용자가 있어야 함
    }

    @Test
    public void testGetUserById() {
        // ID로 사용자 정보를 가져오는 메서드 테스트
        String userId = "kku_1001";
        User user = userService.getUserById(userId);

        assertNotNull(user); // 사용자가 null이 아니어야 함
        assertEquals(userId, user.getId()); // 반환된 사용자의 ID가 일치해야 함
        logger.debug("User name: " + user.getName());
    }

    @Test
    public void testGetUser() {
        // 특정 User 객체로 조회하는 메서드 테스트
        User searchUser = new User();
        searchUser.setId("kku_1001"); // 검색할 사용자의 ID 설정
        User user = userService.getUser(searchUser);

        assertNotNull(user); // 사용자가 null이 아니어야 함
        assertEquals(searchUser.getId(), user.getId()); // 반환된 사용자의 ID가 일치해야 함
        logger.debug("Found user: " + user.getName());
    }

    @Test
    public void testUpdateUser() {
        // 사용자 정보를 업데이트하는 메서드 테스트
        User user = userService.getUserById("kku_1001"); // 기존 사용자 가져오기
        user.setName("새로운 이름"); // 사용자 이름 변경

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser); // 업데이트된 사용자가 null이 아니어야 함
        assertEquals("새로운 이름", updatedUser.getName()); // 이름이 변경되었는지 확인
        logger.debug("Updated user name: " + updatedUser.getName());
    }
}
