package com.example.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.MemberRepository;
import com.example.librarymanagement.entities.Member;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	public List<Member>getAllMembers(){
     return memberRepository.getAllMembers();
	}
	public String addMember(Member member) {
		int result=memberRepository.addMember(member);
		return result>0?"Member added SuccessFully":"Failed to add member";
	}
    public String updatemember(Member member) {
    	int result=memberRepository.updateMember(member);
    	return result>0?"Member details is updated Successfully":"failed to update member";
    }
    public String deletemember(Long id) {
    	int result=memberRepository.deleteMember(id);
    	return result>0?"Member is successfully deleted":"Member is failed to delete";
    	
    }
    public List<Member>searchMember(String keyword){
    	return memberRepository.searchMember(keyword);
    }

}
