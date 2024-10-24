package kr.ac.kku.cs.wp.DaeKuen16.support.sql;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

public class ConnectionUtilTest {
    @Test
    public void testConnection() throws SQLException{
    	try (Connection conn = ConnectionUtil.getConnection()){
    		conn.commit();
    	}catch (Exception e) {
    		throw e;
    	}
    }
}
