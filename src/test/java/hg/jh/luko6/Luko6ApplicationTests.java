package hg.jh.luko6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BoardApplicationTests {

    @Test
    void contextLoads() { //Spring Boot 애플리케이션의 컨텍스트를 로드하는지 test
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource(){ // DataSource가 올바르게 설정되었는지 test
        assertNotNull(dataSource);
    }

    @Test
    public void testJdbcTemplate(){ // 간단한 SQL 쿼리를 실행하여 test
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertNotNull(result);

    }
}