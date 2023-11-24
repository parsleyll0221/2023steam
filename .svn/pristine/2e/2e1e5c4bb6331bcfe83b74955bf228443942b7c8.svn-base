package kr.co.publicvoid.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import kr.co.publicvoid.mapper.MemberMapper;
import kr.co.publicvoid.util.MyBatisSqlSessionFactory;

public class DBTests {
	private SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionFactory.getSqlSessionFactory();
	
	// log4j JSP 설정법 물어보기
	// MyBatis JSP 설정법 물어보기
	// 프로젝트 이상하게 뜨는 거 물어보기
	// 프로젝트 import 해서 처음 설정법 물어보기
	
	@Test
	public void DBTest() {
		SqlSession session = sqlSessionFactory.openSession(true);
		
		System.out.println(session);
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		mapper.getList().forEach(System.out::println);
	}
}
