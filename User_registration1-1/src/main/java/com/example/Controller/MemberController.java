package com.example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.entities.Member;
import com.example.librarymanagement.service.MemberService;

import java.util.List;
@RestController
@RequestMapping("/api/members")
public class MemberController {
	    @Autowired
	    private MemberService memberService;

	    @GetMapping
	    public List<Member> getAllMembers() {
	        return memberService.getAllMembers();
	    }

	    @PostMapping
	    public ResponseEntity<String> addMember(@RequestBody Member member) {
	        memberService.addMember(member);
	        return ResponseEntity.ok("Member added successfully!");
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody Member member) {
	        member.setId(id);
	        memberService.updatemember(member);
	        return ResponseEntity.ok("Member updated successfully!");
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
	        memberService.deletemember(id);
	        return ResponseEntity.ok("Member deleted successfully!");
	    }

	    @GetMapping("/search")
	    public List<Member> searchMembers(@RequestParam String keyword) {
	        return memberService.searchMember(keyword);
	    }
	}



