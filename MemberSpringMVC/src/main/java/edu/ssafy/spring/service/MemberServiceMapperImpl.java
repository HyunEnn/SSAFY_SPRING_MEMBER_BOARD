package edu.ssafy.spring.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ssafy.spring.dto.MemberDto;
import edu.ssafy.spring.reporitory.MemberRepository;
import edu.ssafy.spring.util.PageNavigation;
import edu.ssafy.spring.util.PaggingUtil;

@Service("MemberServiceMapperImpl")
public class MemberServiceMapperImpl implements MemberService{
	
	SqlSession session;
	
	@Autowired
	public MemberServiceMapperImpl(SqlSession session) {
		this.session = session;
	}
	
	@Override
	//@Transactional
	public int memberInsert(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		
		// tx 테스트용
		//session.getMapper(MemberRepository.class).memberInsert(dto);
		return session.getMapper(MemberRepository.class).memberInsert(dto);
	}

	@Override
	public List<MemberDto> memberList() throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(MemberRepository.class).memberList();
	}

	@Override
	public List<MemberDto> memberList(Map<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(MemberRepository.class).memberList(map);
	}

	@Override
	public MemberDto memberView(MemberDto dto) throws Exception {
		
		return session.getMapper(MemberRepository.class).memberView(dto);
	}

	@Override
	public int memberUpdate(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(MemberRepository.class).memberUpdate(dto);
	}

	@Override
	public boolean login(MemberDto dto) throws Exception {
		MemberDto login = session.getMapper(MemberRepository.class).login(dto);
		if(login != null) return true;
		else return false;
		
	}

	@Override
	public int memberDelete(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(MemberRepository.class).memberDelete(dto);
	}

	@Override
	public boolean memberDeletes(String[] ids) throws Exception {
		MemberDto dto = new MemberDto(); 
		for (String id : ids) {
			dto.setId(id);
			session.getMapper(MemberRepository.class).memberDelete(dto);
		}
		return true;
	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage) throws Exception {
		int naviSize = PaggingUtil.naviSize;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalSize = memberCnt();
		//pageNavigation.setTotalCount(totalSize);
		int totalPageSize = (totalSize - 1)/sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageSize);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
	public int memberCnt() throws Exception {
		int cnt = session.selectOne("edu.ssafy.spring.reporitory.MemberRepository.memberCnt");
		return cnt;
	}

}
