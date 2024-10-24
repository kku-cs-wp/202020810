package kr.ac.kku.cs.wp.DaeKuen16.support.sql;

import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;

public class ConnectionPoolUtilTest {

    @Test
    public void testConnection() {
        // 쿼리 작성
        String query = "SELECT * FROM user";

        try (Connection connection = ConnectionPoolUtil.getConnection()) { // 연결 풀에서 커넥션 얻기
            // 커넥션 풀 상태 출력
            ConnectionPoolUtil.printConnectionPoolStatus();

            // PreparedStatement 생성 및 쿼리 실행
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // 결과 출력
            while (rs.next()) {
                System.out.println("User: " + rs.getString("name")); // 예시로 'name' 필드 출력
            }

            // 커넥션 풀 상태 출력
            ConnectionPoolUtil.printConnectionPoolStatus();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 커넥션 풀 상태 출력 및 종료
            ConnectionPoolUtil.printConnectionPoolStatus();
            ConnectionPoolUtil.closeDataSource();
        }
    }
}
