package kr.co.publicvoid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.publicvoid.vo.MemberVO;

public interface MemberMapper {
	
	// 등록
	// 아이디, 비밀번호, 닉네임 수동 등록 
	// 자기소개는 수정에서만 가능
	// 회원 코드 자동부여
	int insert(MemberVO vo);
	
	// 단일 조회
	MemberVO read(Long memberNo);
	MemberVO read2(String string);
	MemberVO selectOne(Long memberNo);
	
	// 전체 조회
	List<MemberVO> getList();
	List<MemberVO> selectList();

	// 수정
	// 아이디, 비밀번호, 닉네임, 자기소개 
	int update(MemberVO vo);
	
	// 삭제
	int delete(Long memberNo);
	
	// 코드 단일 조회
	Integer codeCnt(String memberCode);

	// 아이디 중복 조회
	int selectOneById(String memberId);
	
	// 로그인 조회
	int login(String memberId, String memberPw);
	
	// 아이디, 패스워드 일치 조회
	Integer findIdPw(MemberVO memberVO);
	
	// 관리자 여부 조회
	int isAdmin(Long memberNo);
	
	// 해당 회원의 포인트 증감
	int updatePoint(@Param("memberNo") Long memberNo, @Param("point") int point);
	
	// 해당 회원의 누적구매액 증감
	int updatePurchase(@Param("memberNo") Long memberNo, @Param("purchase") int purchase);
	
	// 해당 회원의 리뷰 수 증감
	int updateReviewCount(@Param("memberNo") Long memberNo, @Param("reviewCount") int reviewCount);
	
}
