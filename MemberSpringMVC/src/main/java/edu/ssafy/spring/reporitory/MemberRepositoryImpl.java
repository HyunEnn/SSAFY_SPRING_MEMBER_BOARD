package edu.ssafy.spring.reporitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.ssafy.spring.dto.MemberDto;

@Repository("MemberRepositoryImpl")
public class MemberRepositoryImpl implements MemberRepository{
	private DataSource source;
	
	@Autowired
	public MemberRepositoryImpl(DataSource source) {
		this.source = source;
	}
	
	public int memberInsert(MemberDto dto) throws Exception {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into members(id, pw, name, age, addr) ");
		sb.append(" values(?,?,?,?,?) ");
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
	
	public List<MemberDto> memberList() throws SQLException  {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw, name, age, addr ");
		sb.append(" from members ");
		
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		List<MemberDto> list = new ArrayList();
		while(rs.next()) {
			list.add(new MemberDto(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getString("age"), rs.getString("addr")));
		}
		conn.close();
		return list;
	}
	public MemberDto memberView(MemberDto dto) throws SQLException{
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw, name, age, addr ");
		sb.append(" from members ");
		sb.append(" where id = ? ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getId());
		ResultSet rs = stmt.executeQuery();
		MemberDto mem = null;
		if(rs.next()) {
			mem = new MemberDto();
			mem.setId(rs.getString("id"));
			mem.setPw(rs.getString("pw")); 
			mem.setName(rs.getString("name"));
			mem.setAddr(rs.getString("addr"));
			mem.setAge(rs.getString("age"));
		}
		conn.close();
		return mem;
	}
	public int memberUpdate(MemberDto dto) throws SQLException{
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" update members set pw = ?, name = ?, age = ?, addr = ? ");
		sb.append(" where id = ?  ");
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
	public int memberDelete(MemberDto dto) throws SQLException{
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from members ");
		sb.append(" where id = ?  ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getId());
		int res = stmt.executeUpdate();
		conn.close();
		return res;
	}

	@Override
	public MemberDto login(MemberDto dto) throws Exception {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw, name, age, addr ");
		sb.append(" from members ");
		sb.append(" where id = ? and pw = ?  ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, dto.getId());
		stmt.setString(2, dto.getPw());
		ResultSet rs = stmt.executeQuery();
		MemberDto res = null;
		if(rs.next()) {
			res = new MemberDto();
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw")); 
			dto.setName(rs.getString("name"));
			dto.setAddr(rs.getString("addr"));
			dto.setAge(rs.getString("age"));
		}
		conn.close();
		return res;
	}

	@Override
	public int memberCnt() throws Exception {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(*) as cnt from members  ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		conn.close();
		return cnt;
	}

	@Override
	public List<MemberDto> memberList(Map<String, Integer> map) throws Exception {
		int currentPage = (int) map.get("currentPage");
		int sizePerPage = (int) map.get("sizePerPage");
		
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id, pw, name, age, addr ");
		sb.append(" from members ");
		sb.append(" limit ?, ?" );
		
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setInt(1, currentPage);
		stmt.setInt(2, sizePerPage);
		ResultSet rs = stmt.executeQuery();
		
		List<MemberDto> list = new ArrayList();
		while(rs.next()) {
			list.add(new MemberDto(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getString("age"), rs.getString("addr")));
		}
		conn.close();
		return list;
	}

	

}
