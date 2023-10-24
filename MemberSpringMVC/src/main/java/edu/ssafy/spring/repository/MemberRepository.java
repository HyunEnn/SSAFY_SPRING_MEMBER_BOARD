package edu.ssafy.spring.repository;

import java.util.List;
import java.util.Map;

import edu.ssafy.spring.dto.MemberDto;

public interface MemberRepository {
	int memberInsert(MemberDto dto) throws Exception;
	List<MemberDto> memberList() throws Exception;
	List<MemberDto> memberList(Map<String,Integer>map) throws Exception;
	MemberDto memberView(MemberDto dto)  throws Exception;
	int memberUpdate(MemberDto dto) throws Exception;
	int memberDelete(MemberDto dto) throws Exception;
	public MemberDto login(MemberDto dto) throws Exception;
	int memberCnt()  throws Exception;
	
}
