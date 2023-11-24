package kr.co.publicvoid.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.publicvoid.service.MemberService;
import kr.co.publicvoid.util.WebUtils;
import kr.co.publicvoid.vo.MemberVO;


@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();

	// 로그인 폼 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}

	// 로그인 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		
		MemberVO memberVO = MemberVO
				.builder()
				.memberId(memberId)
				.memberPw(memberPw)
				.build();

		MemberVO member = memberService.findByMember(memberId);
		
		if(!memberService.duplicateId(memberId)) {
			WebUtils.alertBack(resp, "등록되지 않은 사용자입니다");
			return;
		}
		
		// 아이디와 비밀번호를 조회하여 해당profile하는 아이디와 비밀번호가 일치하지 않거나, 존재하지 않을 경우
		// 로그인 할 수 없는 alert 후 이전 화면으로
		if(!memberService.findIdPw(memberVO)) {
			WebUtils.alertBack(resp, "아이디와 비밀번호가 일치하지 않습니다");
			return;
		}
		
		req.getSession().setAttribute("member", member);
		
		resp.sendRedirect(WebUtils.CONTEXT_PATH + "/");
	}
}
