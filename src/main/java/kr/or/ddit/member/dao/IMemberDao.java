package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {
	public MemberVO getMemberInfo(Map<String, String> params) throws Exception;
	public List<MemberVO> getMemberList(Map<String, String> params) throws Exception;
	public void insertMemberInfo(MemberVO memberInfo) throws Exception;
	public int deleteMemberInfo(Map<String, String> params) throws Exception;
	public int updateMemberInfo(MemberVO memberInfo) throws Exception;
	
	public List<Map<String, String>> getMemberAdd() throws Exception;
}
