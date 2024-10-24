package kr.ac.kku.cs.wp.DaeKuen16.support.sql;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolUtil {
	private static HikariDataSource dataSource;
	static {
		try {
			HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/cswp_202020810?serverTimezone=UTC");
            config.setUsername("DaeKuen16");
            config.setPassword("202020810");
            config.setAutoCommit(false);
            config.setMinimumIdle(3); 
            config.setMaximumPoolSize(10); 
            config.setConnectionTimeout(30000); 
            config.setIdleTimeout(600000); 
            config.setMaxLifetime(1800000);

            dataSource = new HikariDataSource(config);
		} catch (Exception e) {
            e.printStackTrace();
        }

	}
	private static final Logger logger = LogManager.getLogger(ConnectionPoolUtil.class);

	// 커넥션 풀에서 커넥션 얻기
	public static Connection getConnection() throws SQLException {
	    return dataSource.getConnection();
	}

	// 커넥션 풀 상태 확인 (모니터링)
	public static void printConnectionPoolStatus() {
	    logger.debug("Total connections: " + dataSource.getHikariPoolMXBean().getTotalConnections());
	    logger.debug("Active connections: " + dataSource.getHikariPoolMXBean().getActiveConnections());
	    logger.debug("Idle connections: " + dataSource.getHikariPoolMXBean().getIdleConnections());
	}

	// 데이터 소스 종료
	public static void closeDataSource() {
	    if (dataSource != null && !dataSource.isClosed()) {
	        dataSource.close();
	    }
	}
}

