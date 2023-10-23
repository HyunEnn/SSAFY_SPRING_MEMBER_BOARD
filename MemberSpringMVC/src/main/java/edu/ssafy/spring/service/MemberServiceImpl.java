package edu.ssafy.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ssafy.spring.dto.MemberDto;
import edu.ssafy.spring.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	
	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	@Override
	public int memberInsert(MemberDto dto) throws Exception {
		return memberRepository.memberInsert(dto);
	}

	@Override
	public List<MemberDto> memberList() throws Exception {
		return memberRepository.memberList();
	}

	@Override
	public MemberDto memberView(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return memberRepository.memberView(dto);
	}

	@Override
	public int memberUpdate(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return memberRepository.memberUpdate(dto);
	}

	@Override
	public int memberDelete(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return memberRepository.memberDelete(dto);
	}

	@Override
	public MemberDto memberLogin(String id, String pw) throws Exception {
		// TODO Auto-generated method stub
		return memberRepository.memberLogin(id, pw);
	}

	@Override
	public int idCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return memberRepository.idCheck(id);
	}
	
	@Override
	public MemberDto findById(String id) throws Exception {
		return memberRepository.findById(id);
	}

	
}
