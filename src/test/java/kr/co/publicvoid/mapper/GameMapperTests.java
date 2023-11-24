package kr.co.publicvoid.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import kr.co.publicvoid.dto.Criteria;
import kr.co.publicvoid.dto.GameSearchDTO;
import kr.co.publicvoid.util.Encrypt;
import kr.co.publicvoid.util.MybatisUtils;
import kr.co.publicvoid.vo.GameVO;
import kr.co.publicvoid.vo.GenreVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class GameMapperTests {
	private GameMapper mapper = MybatisUtils.sqlSessionFactory().openSession(true).getMapper(GameMapper.class);

	@Test
	public void testExist(){
		assertNotNull(mapper); // 해당 객체가 null이면 빨간색이 뜨면서 테스트 통과 실패
	}
	
	// 게임 연령제한은 0, 1, 2, 3, 4 중 하나만 들어가도록 CHECK 걸었음
	
	// 등록 테스트
	@Test
	public void insertTest() {
		GameVO gameVO = GameVO.builder().genreNo(1L).publisherNo(2L).gameName("테스트").gameDesc("테스트내용").gameVideoUrl("testURL").gamePrice(1000).gameDeveloper("개발사").gameReleaseDate(new Date()).gameAgeLimit(4).gameSpec("권장사양 뭐시기").build();
		
		log.info(mapper.insert(gameVO));
	}
	
	// 수정 테스트
	@Test
	public void updateTest() {
		GameVO gameVO = mapper.selectOne(330L);
		gameVO.setGenreNo(2L);
		gameVO.setPublisherNo(3L);
		gameVO.setGameName("수정함");
		gameVO.setGameDesc("수정한설명");
		gameVO.setGameVideoUrl("modified url");
		gameVO.setGamePrice(20000);
		gameVO.setGameDeveloper("수정한개발사");
		gameVO.setGameReleaseDate(new Date());
		gameVO.setGameAgeLimit(2);
		gameVO.setGameSpec("수정한권장사양");
		gameVO.setGameSaleCount(200);

		log.info(mapper.update(gameVO));
	}
	
	// 삭제 테스트
//	@Test
//	public void deleteTest() {
//		log.info(mapper.delete(332L));
//	}
	@Test
	public void deleteTest() {
		log.info(mapper.delete(881L));
	}
	
	
	
	
	// 목록 조회 테스트
	@Test
	public void selectListTest() {
		mapper.selectList().forEach(log::info);
	}
	
	// 단일 조회 테스트
	@Test
	public void selectOneTest() {
		log.info(mapper.selectOne(330L));
	}

//	private Long genreNo; // 장르번호
//	private int priceLimit; // 최대 가격
//	private List<Long> languageNoList; // 지원언어 리스트
//	private boolean isSale; // 할인상품 여부
//	private String keyword; // 검색어
	
	// 조건으로 목록 필터링하여 조회 테스트
	@Test
	public void selectListByFilterTest() {
		// 받아올 때는 1,2,3, ... 이렇게 구분자를 통해 받아올 것이다
		
		String[] languageNoArr = "1|2|3|4|5|6|7|8|9|10|11|12".split("\\|");
		List<String> languageNoList = Arrays.asList(languageNoArr); 
		
		GameSearchDTO gameSearchDTO = GameSearchDTO.builder()
													.genreNo(1L)
													.priceLimit(100000)
													.languageNoList(languageNoList)
													.isSale(true)
													.keyword(null)
													.build();
		
		mapper.selectListByFilterAndPaging(gameSearchDTO).forEach(log::info);
	}
	
	@Test
	public void selectGameGenre() {
	}
	@Test
	public void testGetListWithPaging() {
		Criteria criteria = new Criteria(1, 10, "T,C,W", "제목");
//		Criteria criteria = new Criteria();
		log.info(criteria);
//		criteria.getTypeArr().length
//		GameMapper.getListWithPaging(new Criteria()).forEach(log::info);
		
		
	}
	
}











