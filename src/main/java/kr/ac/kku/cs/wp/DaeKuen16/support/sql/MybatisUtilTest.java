package kr.ac.kku.cs.wp.DaeKuen16.support.sql;

import static org.junit.Assert.assertNotNull;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kr.ac.kku.cs.wp.DaeKuen16.user.mapper.*;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;

public class MybatisUtilTest {
    
    // 로깅을 위한 Logger 선언
    private static final Logger logger = LogManager.getLogger(MybatisUtilTest.class);

    @Test
    public void test() {
        // MybatisUtil을 통해 SqlSessionFactory를 가져옴
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

        // SqlSession을 열어서 쿼리 실행
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // UserMapper 인터페이스를 가져옴
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 쿼리 실행 (kku_1001을 예시로 사용)
            User user = mapper.getUserById("kku_1001");

            // 결과 검증 및 로그 출력
            assertNotNull(user); // 결과가 null이 아니어야 함
            logger.debug(user.getName()); // user의 이름을 로그로 출력
        }
    }
}
