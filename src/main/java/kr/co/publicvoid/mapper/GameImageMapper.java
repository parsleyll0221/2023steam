package kr.co.publicvoid.mapper;

import java.util.List;

import kr.co.publicvoid.vo.GameImageVO;

public interface GameImageMapper {
	// 등록
	int insert(GameImageVO gameImageVO);
	
	// 수정
	int update(GameImageVO gameImageVO);
	
	// 삭제
	int delete(Long gameImageNo);
	
	// 목록 조회
	List<GameImageVO> selectList();
	
	// 단일 조회
	GameImageVO selectOne(Long gameImageNo);
	
	// 게임 번호로 목록 조회
	List<GameImageVO> selectListByGameNo(Long gameNo);
}
