package kr.ac.kku.cs.wp.DaeKuen16.support.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    // MySQL 연결 정보 설정 (URL, 사용자, 비밀번호)
    private static final String URL = "jdbc:mysql://localhost:3306/cswp_202020810?serverTimezone=UTC";
    private static final String USER = "DaeKuen16";
    private static final String PASSWORD = "202020810";

    // static 영역에서 JDBC 드라이버 로딩
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Connection 반환 메서드
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
