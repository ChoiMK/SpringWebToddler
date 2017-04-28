package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.join.controller.JoinController;
import kr.or.ddit.vo.MemberVO;

//public class IMemberDaoImpl implements IMemberDao, ApplicationContextAware {
// <bean name="memberDao" class="kr.or.ddit.member.dao.IMemberDaoImpl"/>
@Repository("memberDao")
public class IMemberDaoImpl implements IMemberDao {
//	@Resource
	@Autowired
	private SqlMapClient client;
	private static Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	public IMemberDaoImpl() {
		logger.debug("IMemberDaoImpl의 생성자");
	}

//	@Override
//	public void setApplicationContext(ApplicationContext webApplicationConext)
//			throws BeansException {
//		client = (SqlMapClient) webApplicationConext.getBean("sqlMapClient");
//	}

	@Override
	public MemberVO getMemberInfo(Map<String, String> params)
			throws Exception {
		return (MemberVO) client.queryForObject("member.memberInfo", params);
	}

	@Override
	public List<MemberVO> getMemberList(Map<String, String> params) throws Exception {
		return client.queryForList("member.memberList", params);
	}

	@Override
	public void insertMemberInfo(MemberVO memberInfo) throws Exception {
		client.insert("member.insertMember", memberInfo);
	}

	@Override
	public int deleteMemberInfo(Map<String, String> params) throws Exception {
		return client.update("member.deleteMember", params);
	}

	@Override
	public int updateMemberInfo(MemberVO memberInfo) throws Exception {
		return client.update("member.updateMember", memberInfo);
	}

	@Override
	public List<Map<String, String>> getMemberAdd() throws Exception {
		return client.queryForList("member.memberAdd");
	}

	public void initMethod(){
		logger.debug("해당 빈 클래스의 인스턴스화 처리시 생성자 -> setter -> init-method설정 메서드 콜백");
	}
	
	public void destroyMethod(){
		logger.debug("해당 빈 클래스의 GC 직전에 콜백");
	}
}











