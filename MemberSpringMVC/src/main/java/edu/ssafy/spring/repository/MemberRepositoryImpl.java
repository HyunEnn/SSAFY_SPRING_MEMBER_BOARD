package edu.ssafy.spring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.spring.dto.MemberDto;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private DataSource source;
	
	@Autowired 
	public MemberRepositoryImpl(DataSource source) {
		super();
		this.source = source;
	}

	@Override
	public int memberInsert(MemberDto dto) throws Exception {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into members(id, pw, name, age, addr)");
		sb.append(" values(?, ?, ?, ?, ?)");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getId());
		stmt.setString(2, dto.getPw());
		stmt.setString(3, dto.getName());
		stmt.setString(4, dto.getAge());
		stmt.setString(5, dto.getAddr());
		int res = stmt.executeUpdate();
		conn.close();
		return res;
	}

	@Override
	public List<MemberDto> memberList() throws Exception {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw, name, age, addr");
		sb.append(" from members");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		List<MemberDto> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new MemberDto(rs.getNString("id"), rs.getString("pw"), rs.getString("name"), rs.getString("age"), rs.getString("addr")));
			
		}
		conn.close();
		return list;
	}

	@Override
	public MemberDto memberView(MemberDto dto) throws Exception{
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw, name, age, addr");
		sb.append(" from members");
		sb.append(" where id = ? ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setAddr(rs.getString("addr"));
			dto.setAge(rs.getString("age"));
		}
		conn.close();
		return dto;
	}

	@Override
	public int memberUpdate(MemberDto dto) throws Exception{
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" update members set pw = ?, name =?, age = ?, addr = ?");
		sb.append(" where id = ? ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getPw());
		stmt.setString(2, dto.getName());
		stmt.setString(3, dto.getAge());
		stmt.setString(4, dto.getAddr());
		stmt.setString(5, dto.getId());
		int res = stmt.executeUpdate();
		conn.close();
		return res;
	}

	@Override
	public int memberDelete(MemberDto dto) throws Exception {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from members");
		sb.append(" where id = ?");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getId());
		int res = stmt.executeUpdate();
		conn.close();
		return res;
	}

	@Override
	public MemberDto memberLogin(String id, String pw) throws Exception {
		MemberDto dto = null;
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw from members ");
		sb.append(" where id = ? and pw = ?" );
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, id);
		stmt.setString(2, pw);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			dto = new MemberDto();
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
		}
		conn.close();
		return dto;
	}

	@Override
	public int idCheck(String id) throws Exception {
		int cnt = 0;
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(id) ");
		sb.append(" from members ");
		sb.append(" where id = ? ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		cnt = rs.getInt(1);
		return cnt;
	}
	
}
