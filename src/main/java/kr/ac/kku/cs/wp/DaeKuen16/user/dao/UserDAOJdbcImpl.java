package kr.ac.kku.cs.wp.DaeKuen16.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.ac.kku.cs.wp.DaeKuen16.support.sql.ConnectionPoolUtil;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.Role;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRole;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.UserRoleId;

public class UserDAOJdbcImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOJdbcImpl.class);
    
    private final String querySelectById = "SELECT * FROM user WHERE id = ?";
    private final String querySelectUserRole = "SELECT * FROM user_role WHERE user_id = ?";
    private final String querySelectList = "SELECT * FROM user";
    private final String queryInsertUser = "INSERT INTO user (id, name, email, password, status) VALUES (?, ?, ?, ?, ?)";
    private final String queryUpdateUser = "UPDATE user SET name = ?, email = ?, password = ?, status = ? WHERE id = ?";
    private final String queryDeleteUser = "DELETE FROM user WHERE id = ?";
    
    @Override
    public User getUserById(String userId) {
        User user = new User();
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(querySelectById);
            PreparedStatement pStmt = conn.prepareStatement(querySelectUserRole);
            pstmt.setString(1, userId);
            pStmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setStatus(rs.getString(5));
                pStmt.setString(1, user.getId());
                ResultSet rsur = pStmt.executeQuery();
                List<UserRole> urList = new ArrayList<UserRole>();
                while (rsur.next()) {
                    Role role = new Role();
                    role.setId(rsur.getString(2));
                    role.setRole(rsur.getString(3));
                    UserRoleId uri = new UserRoleId();
                    uri.setRoleId(role.getId());
                    uri.setUserId(user.getId());
                    UserRole ur = new UserRole();
                    ur.setRole(role);
                    ur.setUser(user);
                    ur.setId(uri);
                    urList.add(ur);
                }
                rsur.close();
                user.setUserRoles(urList);
            }
            rs.close();
            pStmt.close();
            pstmt.close();
        } catch (SQLException e) {
            logger.error("Error fetching user by ID", e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUser(User user) {
        return getUserById(user.getId());
    }
    
    @Override
    public User updateUser(User user) {
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(queryUpdateUser);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getStatus());
            pstmt.setString(5, user.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("User updated successfully.");
            } else {
                logger.warn("No user found to update.");
            }
            pstmt.close();
        } catch (SQLException e) {
            logger.error("Error updating user", e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(queryDeleteUser);
            pstmt.setString(1, user.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("User deleted successfully.");
            } else {
                logger.warn("No user found to delete.");
            }
            pstmt.close();
        } catch (SQLException e) {
            logger.error("Error deleting user", e);
            e.printStackTrace();
        }
    }

    @Override
    public User createUser(User user) {
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(queryInsertUser);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("User created successfully.");
            } else {
                logger.warn("User creation failed.");
            }
            pstmt.close();
        } catch (SQLException e) {
            logger.error("Error creating user", e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUsers(User pUser) {
        List<User> users = new ArrayList<User>();

        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(querySelectList);
            PreparedStatement pStmt = conn.prepareStatement(querySelectUserRole);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setStatus(rs.getString(5));
                pStmt.setString(1, user.getId());
                ResultSet rsur = pStmt.executeQuery();
                List<UserRole> urList = new ArrayList<UserRole>();
                while (rsur.next()) {
                    Role role = new Role();
                    role.setId(rsur.getString(2));
                    role.setRole(rsur.getString(3));
                    UserRoleId uri = new UserRoleId();
                    uri.setRoleId(role.getId());
                    uri.setUserId(user.getId());
                    UserRole ur = new UserRole();
                    ur.setRole(role);
                    ur.setUser(user);
                    ur.setId(uri);
                    urList.add(ur);
                }
                rsur.close();
                user.setUserRoles(urList);
                users.add(user);
            }
            rs.close();
            pStmt.close();
            pstmt.close();
        } catch (SQLException e) {
            logger.error("Error fetching users", e);
            e.printStackTrace();
        }

        return users;
    }
}
