package edu.ssafy.spring;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.ssafy.spring.controller.MemberController;
import edu.ssafy.spring.reporitory.MemberRepository;
import edu.ssafy.spring.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/servlet-context.xml"})
public class MVCTest {

	@Autowired
	MemberController conn;
	@Autowired
	MemberService service;
	@Autowired
	@Qualifier("MemberRepositoryMybatis")
	MemberRepository repo;
	
	@Test
	public void testController() {
		assertNotNull(conn);
	}
	
	@Test
	public void testService() {
		assertNotNull(service);
	}
	
	@Test
	public void testRepository() {
		assertNotNull(repo);
	}
}
