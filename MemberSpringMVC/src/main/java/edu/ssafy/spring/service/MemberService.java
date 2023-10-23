package edu.ssafy.spring.service;

import java.util.List;

import edu.ssafy.spring.dto.MemberDto;

public interface MemberService {
	int memberInsert(MemberDto dto) throws Exception;
	List<MemberDto> memberList() throws Exception;
	MemberDto memberView(MemberDto dto) throws Exception;
	int memberUpdate(MemberDto dto) throws Exception;
	int memberDelete(MemberDto dto) throws Exception;
	MemberDto memberLogin(String id, String pw) throws Exception;
	int idCheck(String id) throws Exception; 
}

