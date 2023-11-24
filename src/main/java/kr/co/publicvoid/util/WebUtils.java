package kr.co.publicvoid.util;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import kr.co.publicvoid.dto.BoardCriteria;
import kr.co.publicvoid.dto.RestResponseDTO;
import kr.co.publicvoid.vo.MemberVO;

public class WebUtils {
	
	// 컨텍스트 패스
	public static final String CONTEXT_PATH = "/steam";
	
	// View 앞단에 고정된 URL
	public static final String VIEW_PATH = "/WEB-INF/views";
	
	// Gson 객체 생성
	private static final Gson gson = new Gson();

	// 게시판 Criteria
	public static BoardCriteria getCriteria(HttpServletRequest req) {
		String category = req.getParameter("category");
		String pageNum = req.getParameter("pageNum");
		String amount = req.getParameter("amount");
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");

		BoardCriteria.BoardCriteriaBuilder cb = BoardCriteria.builder();

		if (category != null && !category.equals("")) {
			cb.category(Integer.parseInt(category));
		}
		if (pageNum != null && !pageNum.equals("")) {
			cb.pageNum(Integer.parseInt(pageNum));
		}
		if (amount != null && !amount.equals("")) {
			cb.amount(Integer.parseInt(amount));
		}
		if (type != null) {
			cb.type(type);
		}
		if (keyword != null) {
			cb.keyword(keyword);
		}

		BoardCriteria criteria = cb.build();

		return criteria;
	}

	// alert창 띄우며 URL 이동
	public static void alert(HttpServletResponse resp, String msg) throws IOException {
		alert(resp, msg, null);
	}

	public static void alert(HttpServletResponse resp, String msg, String url) throws IOException {
		resp.setContentType("text/html; charset=utf-8");

		System.out.println(url);

		PrintWriter pw = resp.getWriter();
		pw.write("<script>alert('" + msg + "');");
		if (url != null) {
			pw.write("location.href = '" + CONTEXT_PATH + url + "'");
		}
		pw.write("</script>");
	}

	public static void alertBack(HttpServletResponse resp, String msg) throws IOException {
		resp.setContentType("text/html; charset=utf-8");

		PrintWriter pw = resp.getWriter();
		pw.write("<script>alert('" + msg + "'); history.back();</script>");
	}

	// gson 객체를 받아 json 객체로 출력
	public static void jsonPrint(HttpServletResponse resp, Object o) throws IOException{
		// JSON 형태로 데이터 응답
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		// try-with-resources
		try (PrintWriter out = resp.getWriter()) {
			String json = gson.toJson(o);
			out.print(json);
		}
	}
	
	// gson 객체를 받아 json 객체를 출력 - REST용
	public static void jsonPrintREST(HttpServletResponse resp, boolean success, int errorType, String msg, Object o) throws IOException{
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		RestResponseDTO responseData = new RestResponseDTO();
		responseData.setSuccess(success);
		responseData.setMessage(msg);
		
		if(success == false) {
			responseData.setErrorType(errorType);
		}else {
			responseData.setData(o);
		}

		// try-with-resources
		try (PrintWriter out = resp.getWriter()) {
			String json = gson.toJson(responseData);
			out.print(json);
		}
	}
	
	// 로그인 여부
	public static MemberVO getMember(HttpServletRequest req) {
        MemberVO member = null;
        if (req.getSession().getAttribute("member") != null) {
            member = (MemberVO)req.getSession().getAttribute("member");
        }
        return member;
    }
	
	// 현재 로그인 회원 불러오기
	public static MemberVO getLoginMember(HttpServletRequest req) {
		MemberVO memberVO = null;
		
		if(req.getSession().getAttribute("member") != null) {
			memberVO = (MemberVO)req.getSession().getAttribute("member");
		}
		
		return memberVO;
	}
	
	// Member 객체끼리 비교해서 같은 회원인지 비교
	public static boolean isSameMember(MemberVO member1, MemberVO member2) {
		if(member1.getMemberNo() == member2.getMemberNo()) { // 자기 자신이면
			return true;
		}else {
			return false;
		}
	}
}










