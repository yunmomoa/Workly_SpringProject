package com.workly.final_project.member.model.service;

import java.util.List;

import com.workly.final_project.common.model.vo.Attachment;
import com.workly.final_project.common.model.vo.PageInfo;
import com.workly.final_project.member.model.dto.ChangePwd;
import com.workly.final_project.member.model.dto.MemberDTO;
import com.workly.final_project.member.model.vo.CategoryFilter;
import com.workly.final_project.member.model.vo.Department;
import com.workly.final_project.member.model.vo.Member;
import com.workly.final_project.member.model.vo.Position;

public interface MemberService {

	MemberDTO loginMember(Member m);

	List<Member> selectMemberList(PageInfo pi, CategoryFilter filter);

	int selectMemberCount(CategoryFilter filter);
	
	MemberDTO selectMember(int userNo);

	List<Department> selectDeptList();

	List<Position> selectPosiList();

	int updateMember(Member m);
	
	int updateMember(Member m, Attachment at);

	int checkAttachment(Member m);

	int updateMember(Attachment at, Member m);

	List<MemberDTO> selectModalMemberList();

	boolean currentPwdCheck(int userNo, String currentPwd);

	int updatePassword(ChangePwd changePwd);
}
