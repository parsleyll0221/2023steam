package kr.co.publicvoid.service;

import org.apache.ibatis.session.SqlSession;

import kr.co.publicvoid.mapper.SaleMapper;
import kr.co.publicvoid.util.MybatisUtils;
import kr.co.publicvoid.vo.SaleVO;

public class SaleService {
	public static final SaleService INSTANCE = new SaleService();

	private SaleService() {}
	
	public static SaleService getInstance() {
		return INSTANCE;
	}
	
	public SaleVO getOneByGameNo(Long gameNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			SaleMapper saleMapper = sqlSession.getMapper(SaleMapper.class);
			SaleVO saleVO = saleMapper.selectOneByGameNo(gameNo);

			return saleVO;
		}
	}
	
	public SaleVO getOneByGameNoNow(Long gameNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			SaleMapper saleMapper = sqlSession.getMapper(SaleMapper.class);
			SaleVO saleVO = saleMapper.selectOneByGameNoNow(gameNo);

			return saleVO;
		}
	}
}
