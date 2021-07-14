package board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.service.BoardService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller //servlet-context.xml 에 등록시켜준다
@RequestMapping(value="board")
public class BoardController {
	 @Autowired
	 private BoardService boardService; //인터페이스 잡는다, DB로 간다

		@RequestMapping(value="boardWriteForm", method=RequestMethod.GET)
		public String boardWriteForm(Model model) {
			model.addAttribute("display", "/board/boardWriteForm.jsp");
			return "/index";
		}
	 
		@RequestMapping(value="boardWrite", method=RequestMethod.POST)
		@ResponseBody
		public void boardWrite(@RequestParam Map<String, String> map) {//데이터 2개 오니까 맵으로 받자
			//원글 - 1페이지, 첫번째 (글 작성하면 최신글이니 1페이지의 맨 위로 올라간다)
			boardService.boardWrite(map);
		}
		
		@RequestMapping(value="boardList", method=RequestMethod.GET)
		public String boardList(@RequestParam(required=false, defaultValue="1") 
								String pg, 
								Model model) {//안들어오면 1페이지로 인식해라
			model.addAttribute("pg", pg);
			model.addAttribute("display", "/board/boardList.jsp");
			
			return "/index";
		}
		
		@RequestMapping(value="getBoardList", method=RequestMethod.POST)
		@ResponseBody //json 배열로 줘야한다
		public ModelAndView getBoardList(@RequestParam(required=false, defaultValue="1") String pg,
										HttpSession session,
										HttpServletResponse response) {
			//1페이지당 5개씩
			List<BoardDTO> list = boardService.getBoardList(pg); //보드DTO에 담는다
			
			//세션
			String memId = (String) session.getAttribute("memId");
			
			//페이징 처리
			BoardPaging boardPaging = boardService.boardPaging(pg); //메소드만 호출함
			
			//조회수 - 새로고침 방지
			if(session.getAttribute("memId") != null) { //세션이 존재한다면 - 로그인 안하면 못 들어오게 해서 안 써도 상관은 없다
				Cookie cookie = new Cookie("memHit", "0"); //쿠키 생성
				cookie.setMaxAge(30*60); //쿠키에게 시간을 줌, 초 단위 / 30분
				response.addCookie(cookie);//클라이언트 보내기
			}		
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("pg", pg);
			mav.addObject("list", list);
			mav.addObject("memId", memId);
			mav.addObject("boardPaging", boardPaging);
		    
			mav.setViewName("jsonView");
			return mav;
			}
		
		@RequestMapping(value="getBoardSearchList", method=RequestMethod.POST)
		@ResponseBody  //이거 줘야지 ajax로 간다
		public ModelAndView getBoardSearchList(@RequestParam Map<String, String> map,
												HttpSession session) { //넘어오는 거: pg, searchOption, keyword (form에 다 묶여 있던 거)
			//1페이지당 5개씩
			List<BoardDTO> list = boardService.getBoardSearchList(map); //getBoardSearchList에서 map을 들고 가서 5글을 끌고 와라
			
			//세션
			String memId = (String) session.getAttribute("memId");
			
			//페이징 처리
			BoardPaging boardPaging = boardService.boardPaging(map);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("pg", map.get("pg"));
			mav.addObject("list", list);
			mav.addObject("boardPaging", boardPaging);
			mav.setViewName("jsonView");
			return mav;
		}
		
		//보드뷰 폼 컨트롤러에 등록 -> 보드 뷰.jsp에 ajax -> 보드 컨트롤러 getboard 등록
		@RequestMapping(value="boardView", method=RequestMethod.GET)
		public String boardView(@RequestParam String seq, 
								@RequestParam String pg,
								HttpSession session,
								Model model) { //데이터 2개를 나에게 준다
			//세션
			String memId = (String) session.getAttribute("memId");
			
			model.addAttribute("seq", seq); //seq를 모델에 넣어줌
			model.addAttribute("pg", pg);
			
			model.addAttribute("display", "/board/boardView.jsp");
			return "/index";
		}
		
		//한 사람의 글을 가져오는 역할: getBoard
		@RequestMapping(value="getBoard", method=RequestMethod.POST)
		@ResponseBody //json 배열로 줘야한다
		public ModelAndView getBoard(@RequestParam String seq, 
									@CookieValue(value="memHit", required=false) Cookie cookie,
									HttpSession session,
									HttpServletResponse response) {//boardView data에서 seq 하나만 줌
			//getBoard에서 쿠키 해지해야 함 / 'memHit'라는 cookie 값을 가져온다(@CookieValue), 쿠키가 들어올수도 안 들어올 수 있으니 required=false를 준다
			//required=false 안 주면 에러 뜬다 꼭 넣자
			
			//조회수 - 새로고침 방지
			if(cookie != null) {
				boardService.hitUpdate(seq); //조회수 증가
				cookie.setMaxAge(0); //쿠키 삭제
				response.addCookie(cookie);//클라이언트 보내기
			}
			
			BoardDTO boardDTO = boardService.getBoard(seq);
			
			//세션
			String memId = (String) session.getAttribute("memId");
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("boardDTO", boardDTO);
			mav.addObject("memId", memId);
			mav.setViewName("jsonView");
			return mav;
		}
		
		@RequestMapping(value="boardModifyForm", method=RequestMethod.POST) //보드뷰에서 POST로 받으라고 해서 컨트롤러도 POST로 바꿈
		public String boardModifyForm(@RequestParam String seq,
									@RequestParam String pg,
									Model model) { //데이터를 받아야 함
			//싣고 다님
			model.addAttribute("seq", seq);
			model.addAttribute("pg", pg);
			model.addAttribute("display", "/board/boardModifyForm.jsp");
			return "/index";
		}
		
		@RequestMapping(value="boardModify", method=RequestMethod.POST)
		@ResponseBody //이거 걸면 modifyform으로 간다
		public void boardModify(@RequestParam Map<String, Object> map) {//데이터 2개 오니까 맵으로 받자
			boardService.boardModify(map);
		} //write랑 modify랑 똑같다
		
		@RequestMapping(value="boardReplyForm", method=RequestMethod.POST)
		public String boardReplyForm(@RequestParam String seq,
									@RequestParam String pg,
									Model model) {
			//답글은 1페이지 첫번째가 아니다, 답글의 위치는 원글이 있는 페이지, 원글 밑이다
			model.addAttribute("pseq",seq); //원글번호
			model.addAttribute("pg", pg); //원글이 있는 페이지 번호
			model.addAttribute("display", "/board/boardReplyForm.jsp");
			return "/index";
		}
		
		@RequestMapping(value="boardReply", method=RequestMethod.POST)
		@ResponseBody
		public void boardReply(@RequestParam Map<String, String> map) {//맵 안에 4개가 들어온다: pseq, pg, subject, content
			boardService.boardReply(map);
		}
		
		//얘는 ajax 안 타고 옴 그냥 db로 감
		@RequestMapping(value="boardDelete", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView boardDelete(@RequestParam String seq) { //hidden에서 seq, pg값을 보내는데 여기선 seq만 받으면 됨
			boardService.boardDelete(seq); //seq 들고 가서 삭제를 함
			
			return new ModelAndView("redirect:/board/boardList");
		}
		
}
