package edu.ssafy.spring.repository;

import java.util.*;
import edu.ssafy.spring.dto.MemberDto;

public interface MemberRepository {
	int memberInsert(MemberDto dto) throws Exception;
	List<MemberDto> memberList() throws Exception;
	MemberDto memberView(MemberDto dto) throws Exception;
	int memberUpdate(MemberDto dto) throws Exception;
	int memberDelete(MemberDto dto) throws Exception;
	MemberDto memberLogin(String id, String pw) throws Exception;
	int idCheck(String id) throws Exception;
}
