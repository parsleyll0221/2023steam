package kr.co.publicvoid.controller.board;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import kr.co.publicvoid.mapper.BoardMapper;
import kr.co.publicvoid.service.BoardService;
import kr.co.publicvoid.util.WebUtils;
import kr.co.publicvoid.vo.BoardVO;
import kr.co.publicvoid.vo.MemberVO;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

//@Log4j
@Slf4j
@WebServlet("/board/boardwrite")
public class BoardWriteController extends HttpServlet{
	private BoardService boardService = BoardService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BoardVO> boardList = boardService.getList(new BoardVO());
		System.out.println(boardService);
//		List<BoardVO> boardList = null;
		
		req.setAttribute("boardList", boardList);

		req.getRequestDispatcher("/WEB-INF/views/board/boardwrite.jsp").forward(req, resp);
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		// 한글깨짐 encoding 설정
		req.setCharacterEncoding("utf-8");
		
		// 로그찍기
		log.info("=================================");
		Map<String, String[]> map = req.getParameterMap();
		Iterator<String> keylist = map.keySet().iterator();
		log.info("req.getParameterMap().size() = "+map.size());
		while(keylist.hasNext()) {
			String key = keylist.next();
			log.info(key);
			for(String val : map.get(key))
				log.info("      value ["+val+"]");
		}
		
		// 실제 로직
	
		MemberVO member = WebUtils.getMember(req);
		try {
		if(member.getMemberNo() != null) {
			 
		    Long memberNo = member.getMemberNo();
		    String boardTitle = req.getParameter("boardTitle");
		    String boardContent = req.getParameter("boardContent");
		    String nickname = member.getMemberNickname();
		    
		    BoardVO board = BoardVO.builder()
			        .memberNo(memberNo)
			        .nickname(nickname)
			        .boardCategory("qna") 
			        .boardTitle(boardTitle)
			        .boardContent(boardContent)
			        .build();
		    
		    // insert query
		    boardService.insert(board);  
		    
		    // QNA 데이터 재조회(특정 화면으로 이동하고자 하는 경우 재조회를 통해 데이터를 다시 조회하여 넘김)
		    List<BoardVO> boardList = boardService.getList(new BoardVO());
			System.out.println(boardService);
			req.setAttribute("boardList", boardList);
	 		}
		}catch(Exception e) {
			e.printStackTrace();
		}
 		resp.sendRedirect("qna");
	}
}
