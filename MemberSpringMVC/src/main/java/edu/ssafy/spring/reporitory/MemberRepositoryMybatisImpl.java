package edu.ssafy.spring.reporitory;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.spring.dto.MemberDto;

@Repository("MemberRepositoryMybatis")
public class MemberRepositoryMybatisImpl implements MemberRepository{
	
	SqlSession session;
	
	@Autowired
	public MemberRepositoryMybatisImpl(SqlSession session){
		this.session = session;
		System.out.println(session);
	}
	
	String ns = "edu.ssafy.spring.reporitory.MemberRepository.";
	@Override
	public int memberInsert(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return session.insert(ns+"memberInsert",dto);
	}

	@Override
	public List<MemberDto> memberList() throws Exception {
		List<MemberDto> selectList = session.selectList(ns+"memberList");
		return selectList;
	}

	@Override
	public List<MemberDto> memberList(Map<String, Integer> map) throws Exception {
		List<MemberDto> selectList = session.selectList(ns+"memberListPage",map);
		return selectList;
		
	}

	@Override
	public MemberDto memberView(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		MemberDto memView = session.selectOne(ns + "memberView", dto);
		return memView;
	}

	@Override
	public int memberUpdate(MemberDto dto) throws Exception {
		int memUpdate = session.update(ns + "memberUpdate", dto);
		return memUpdate;
	}

	@Override
	public int memberDelete(MemberDto dto) throws Exception {
		int memDelete = session.delete(ns + "memberDelete", dto);
		return memDelete;
	}

	@Override
	public MemberDto login(MemberDto dto) throws Exception {
		MemberDto memLogin = session.selectOne(ns + "memberLogin", dto);
		return memLogin;
	}

	@Override
	public int memberCnt() throws Exception {
		int cnt = session.selectOne(ns +"memberCnt");
		return cnt;
	}

}
