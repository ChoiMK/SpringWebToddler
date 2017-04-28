package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.join.controller.JoinController;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.IMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

//public class IMemberServiceImpl implements IMemberService, ApplicationContextAware {
// <bean name="memberService" class="kr.or.ddit.member.service.IMemberServiceImpl"/>
@Service("memberService")
public class IMemberServiceImpl implements IMemberService{
	
//	@Resource
	@Autowired
	private IMemberDao dao;
	private static Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	public IMemberServiceImpl() {
		logger.debug("IMemberServiceImpl의 생성자");
	}
	
//	@Override
//	public void setApplicationContext(ApplicationContext context)
//			throws BeansException {
//		this.dao = context.getBean("memberDao", IMemberDao.class);
//	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public MemberVO getMemberInfo(Map<String, String> params) throws Exception{
		MemberVO memberInfo = dao.getMemberInfo(params);
		return memberInfo;
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public List<MemberVO> getMemberList(Map<String, String> params) throws Exception{
		List<MemberVO> memberList = dao.getMemberList(params);
		return memberList;
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void insertMemberInfo(MemberVO memberInfo) throws Exception{
		dao.insertMemberInfo(memberInfo);
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public int deleteMemberInfo(Map<String, String> params) throws Exception{
		int deleteCnt = dao.deleteMemberInfo(params);
		return deleteCnt;
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public int updateMemberInfo(MemberVO memberInfo) throws Exception{
		int updateCnt = dao.updateMemberInfo(memberInfo);
		return updateCnt;
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public List<Map<String, String>> getMemberAdd() throws Exception{
		List<Map<String, String>> memberInfo = dao.getMemberAdd();
		return memberInfo;
	}

	public void initMethod(){
		logger.debug("해당 빈 클래스의 인스턴스화 처리시 생성자 -> setter -> init-method설정 메서드 콜백");
	}
	
	public void destroyMethod(){
		logger.debug("해당 빈 클래스의 GC 직전에 콜백");
	}
}










