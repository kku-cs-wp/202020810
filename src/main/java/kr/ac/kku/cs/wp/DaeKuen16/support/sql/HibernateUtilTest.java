package kr.ac.kku.cs.wp.DaeKuen16.support.sql;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HibernateUtilTest {

    private static SessionFactory sessionFactory;

    // 테스트 시작 전에 세션 팩토리 초기화
    @BeforeClass
    public static void setUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    // 세션 팩토리가 성공적으로 생성되었는지 테스트
    @Test
    public void testSessionFactoryCreation() {
        assertNotNull("세션 팩토리가 null입니다.", sessionFactory);
    }

    // 세션 팩토리의 현재 상태를 테스트
    @Test
    public void testSessionFactoryIsOpen() {
        assertTrue("세션 팩토리가 열려 있지 않습니다.", sessionFactory.isOpen());
    }

    // 테스트 종료 후 세션 팩토리를 닫는지 확인
    @AfterClass
    public static void tearDown() {
        HibernateUtil.shutdown();
        assertFalse("세션 팩토리가 닫히지 않았습니다.", sessionFactory.isOpen());
    }
}
