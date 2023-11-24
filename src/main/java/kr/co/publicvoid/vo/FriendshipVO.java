package kr.co.publicvoid.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 친구 요청 및 차단 VO
 * 
 * @author 황정민
 * 231017
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipVO { // 친구, 친구요청, 차단
	private Long friendshipNo; // 요청번호
	private Long requesterNo; // 요청회원번호
	private Long receiverNo; // 응답회원번호
	private boolean isBlock; // 차단여부
	private Date regDate; // 등록일자
}
