package kr.co.publicvoid.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.publicvoid.dto.LibraryFilterDTO;
import kr.co.publicvoid.mapper.LibraryMapper;
import kr.co.publicvoid.util.MybatisUtils;
import kr.co.publicvoid.vo.LibraryVO;

public class LibraryService {
	public static final LibraryService INSTANCE = new LibraryService();
	
	private LibraryService() {}
	
	public static LibraryService getInstance() {
		return INSTANCE;
	}
	
	// 단일 조회
	public LibraryVO getOne(Long libraryNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			LibraryVO libraryVO = libraryMapper.selectOne(libraryNo);

			return libraryVO;
		}
	}
	
	// 회원 번호로 목록 조회
	public List<LibraryVO> getListByMemberNo(Long memberNo){
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			List<LibraryVO> libraryList = libraryMapper.selectListByMemberNo(memberNo);

			return libraryList;
		}
	}
	
	// 회원 번호로 목록 3개만 조회
	public List<LibraryVO> getListByMemberNoLimit3(Long memberNo){
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			List<LibraryVO> libraryList = libraryMapper.selectListByMemberNoLimit3(memberNo);

			return libraryList;
		}
	}
	
	// 회원 번호로 개수 조회
	public int getCountByMemberNo(Long memberNo){
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int count = libraryMapper.selectCountByMemberNo(memberNo);

			return count;
		}
	}
	
	// 회원 번호, 게임 번호로 라이브러리 조회
	public LibraryVO getOneByMemberNoAndGameNo(Long memberNo, Long gameNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			LibraryVO libraryVO = libraryMapper.selectOneByMemberNoAndGameNo(memberNo, gameNo);

			return libraryVO;
		}
	}
	
	// 라이브러리에 추가
	public int register(LibraryVO libraryVO) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession()) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int result = libraryMapper.insert(libraryVO);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}

			return result;
		}
	}
	
	// 라이브러리에서 삭제
	public int delete(Long libraryNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession()) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int result = libraryMapper.delete(libraryNo);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}

			return result;
		}
	}
	
	// LibraryFilterDTO로 라이브러리 목록 조회
	public List<LibraryVO> getListByLibraryFilterDTO(LibraryFilterDTO libraryFilterDTO){
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession(true)) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			List<LibraryVO> libraryList = libraryMapper.selectListByLibraryFilterDTO(libraryFilterDTO);

			return libraryList;
		}
	}
	
	// 라이브러리 업데이트 (숨김 처리)
	public int updateSetHidden(Long libraryNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession()) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int result = libraryMapper.updateSetHidden(libraryNo);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}

			return result;
		}
	}

	// 라이브러리 업데이트 (숨김 해제 처리)
	public int updateUnsetHidden(Long libraryNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession()) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int result = libraryMapper.updateUnsetHidden(libraryNo);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}

			return result;
		}
	}

	// 라이브러리 업데이트 (즐겨찾기 처리)
	public int updateSetFavorite(Long libraryNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession()) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int result = libraryMapper.updateSetFavorite(libraryNo);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}

			return result;
		}
	}

	// 라이브러리 업데이트 (즐겨찾기 해제 처리)
	public int updateUnsetFavorite(Long libraryNo) {
		try (SqlSession sqlSession = MybatisUtils.sqlSessionFactory().openSession()) {
			LibraryMapper libraryMapper = sqlSession.getMapper(LibraryMapper.class);
			int result = libraryMapper.updateUnsetFavorite(libraryNo);

			if (result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}

			return result;
		}
	}
}










