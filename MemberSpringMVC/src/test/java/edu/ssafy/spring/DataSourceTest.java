package edu.ssafy.spring;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/servlet-context.xml"})
public class DataSourceTest {
	
	@Autowired
	DataSource ds;
	
	@Test
	public void testDataSource() throws SQLException { 
		// given
		System.out.println(ds.toString());
		assertNotNull(ds);
		// when
		
		// then
	}
	
	@Test
	public void testDataSource2() throws SQLException {
		// given
		Connection conn = ds.getConnection();
		String sql = "select * from members";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// when
		ResultSet rs = pstmt.executeQuery();
		// then
		while(rs.next()) {
			System.out.println(rs.getString(1) + "," + rs.getString(2));
		}
	}
	
}
