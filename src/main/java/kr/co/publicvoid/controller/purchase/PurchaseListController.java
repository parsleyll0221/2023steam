package kr.co.publicvoid.controller.purchase;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.publicvoid.service.GameService;
import kr.co.publicvoid.service.MemberService;
import kr.co.publicvoid.service.PurchaseService;
import kr.co.publicvoid.util.WebUtils;
import kr.co.publicvoid.vo.GameVO;
import kr.co.publicvoid.vo.MemberVO;
import kr.co.publicvoid.vo.PurchaseVO;

@WebServlet("/purchase/list")
public class PurchaseListController extends HttpServlet{
	
	private PurchaseService purchaseService = PurchaseService.getInstance();
	private MemberService memberService = MemberService.getInstance();
	private GameService gameService = GameService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 구매내역은 본인만 볼 수 있음 (로그인 필수)

		MemberVO memberVO = WebUtils.getLoginMember(req);
		
		if(memberVO == null) {
			WebUtils.alert(resp, "로그인해주세요", "/member/login");
			return;
		}
		
		Long memberNo = memberVO.getMemberNo();
		
		memberVO = memberService.getOne(memberNo);
		
		// 구매 내역과 해당 게임에 대한 정보가 있어야 함
		List<PurchaseVO> purchaseList = purchaseService.getListByMemberNo(memberNo);
		
		purchaseList.stream().map(purchase -> {
			GameVO gameVO = gameService.getOneReq(purchase.getGameNo());
			purchase.setGameVO(gameVO);
			
			// 현재 라이브러리에 있는지 없는지 여부로 환불 가능 여부를 정한다
			int count = purchaseService.hasRefund(purchase.getPurchaseNo());
			purchase.setHasRefund(count > 0 ? true : false);
			
			return purchase;
		}).collect(Collectors.toList());
		
		req.setAttribute("memberVO", memberVO);
		req.setAttribute("purchaseList", purchaseList);

		req.getRequestDispatcher(WebUtils.VIEW_PATH + "/purchase/list.jsp").forward(req, resp);
		
	}
}





















