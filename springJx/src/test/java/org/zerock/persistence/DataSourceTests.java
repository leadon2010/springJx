package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
public class DataSourceTests {

	@Setter(onMethod_ = { @Autowired })
	private DataSource dataSource;

	@Test
	public void testConnections() {
		try (Connection conn = dataSource.getConnection()) {
			log.info("dataSource--->");
			log.info(conn);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void testMyBatis() {

		try (SqlSession sqlSession = sqlSessionFactory.openSession(); Connection conn = sqlSession.getConnection();) {
			log.info("sqlSession --->");
			log.info(sqlSession);
			log.info(conn);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
