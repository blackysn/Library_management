package com.example.Repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.entities.Member;



@Repository
public class MemberRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Transactional
	public List<Member>getAllMembers(){
		String sql="SELECT *FROM Members";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
	}
	public int addMember(Member member) {
		String sql="INSERT INTO Members (name,phone,registered_date)VALUES(?,?,?)";
		return jdbcTemplate.update(sql,member.getName(),member.getPhone(),member.getRegisteredDate());
				
	}
	public int updateMember(Member member) {
		try {
		String sql="UPDATE Members SET name=?,phone=?,registered_date=? WHERE id= ?";
		return jdbcTemplate.update(sql,member.getName(),member.getPhone(),member.getRegisteredDate(),member.getId());
	}catch(Exception e) {
		throw new RuntimeException("Error adding Member",e);
	}}
	public int deleteMember(Long memberid) {
	    String sql="DELETE FROM  Members WHERE id=?";
	    return jdbcTemplate.update(sql,memberid);
	}
	public List<Member> searchMember(String keyword) {
	    String sql = "SELECT * FROM members WHERE name ILIKE ? OR phone ILIKE ? OR registered_date = ?";

	    String queryParam = "%" + keyword + "%";  // For name and phone ILIKE search
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class), queryParam, queryParam, keyword);
	}

	}

