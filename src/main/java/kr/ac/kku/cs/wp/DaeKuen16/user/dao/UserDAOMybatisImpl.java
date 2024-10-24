package kr.ac.kku.cs.wp.DaeKuen16.user.dao;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.ac.kku.cs.wp.DaeKuen16.user.mapper.UserMapper;
import kr.ac.kku.cs.wp.DaeKuen16.support.sql.MybatisUtil;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.Role;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRole;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRoleId;

public class UserDAOMybatisImpl implements UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAOMybatisImpl.class);
			
	@Override
	public User getUserById(String userId) {
	    User user = null;
	    SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

	    try (SqlSession session = sqlSessionFactory.openSession()) {
	        UserMapper mapper = session.getMapper(UserMapper.class);

	        user = mapper.getUserById(userId);
	        List<Map> userRoles = mapper.getUserRole(user.getId());
	        List<UserRole> urList = new ArrayList<UserRole>();

	        for (Map userRole : userRoles) {
	            Role role = new Role();
	            role.setId((String) userRole.get("roleId"));
	            role.setRole((String) userRole.get("role"));
	            UserRoleId uri = new UserRoleId();
	            uri.setRoleId(role.getId());
	            uri.setUserId(user.getId());
	            UserRole ur = new UserRole();
	            ur.setUser(user);
	            ur.setRole(role);
	            ur.setId(uri);
	            urList.add(ur);
	        }
	        user.setUserRoles(urList);
	    }

	    return user;
	}
	@Override
    public User getUser(User user) {
		return null;
	}
	@Override
    public User updateUser(User user){
		return null;
	}
	@Override
    public void deleteUser(User user){
	}
	@Override
    public User createUser(User user){
		return null;
	}
	@Override
	public List<User> getUsers(User pUser) {
	    List<User> users = null;
	    SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

	    try (SqlSession session = sqlSessionFactory.openSession()) {
	        UserMapper mapper = session.getMapper(UserMapper.class);

	        users = mapper.getUsers(pUser);
	        for (User user : users) {
	            List<Map> userRoles = mapper.getUserRole(user.getId());
	            List<UserRole> urList = new ArrayList<UserRole>();

	            for (Map userRole : userRoles) {
	                Role role = new Role();
	                role.setId((String) userRole.get("roleId"));
	                role.setRole((String) userRole.get("role"));

	                UserRoleId uri = new UserRoleId();
	                uri.setRoleId(role.getId());
	                uri.setUserId(user.getId());

	                UserRole ur = new UserRole();
	                ur.setUser(user);
	                ur.setRole(role);
	                ur.setId(uri);

	                urList.add(ur);
	            }
	            user.setUserRoles(urList);
	        }
	    }
	    return users;
	}


}
