package edu.ssafy.spring;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.ssafy.spring.dto.MemberDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/servlet-context.xml"})
public class MybatisTest {
	
	@Autowired
	SqlSession session;
	
	@Test
	public void testSession() {
		// given
		assertNotNull(session);
	}
	
	String ns = "edu.ssafy.spring.reporitory.MemberRepository.";
	@Test
	public void test1() {
		Map<String, String> map = new HashMap<>();
		map.put("id", "2");
		List<MemberDto> list = session.selectList(ns + "dynamictest1", map);
		System.out.println(list.toString());
		assertNotNull(list);
	}
}
