package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private HttpSession session;
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardPaging boardPaging;

	@Override
	public void boardWrite(Map<String, String> map) {
		map.put("id", (String)session.getAttribute("memId"));
		map.put("name", (String)session.getAttribute("memName"));
		map.put("email", (String)session.getAttribute("memEmail"));
		
		boardDAO.boardWrite(map);
		
	}

	@Override
	public List<BoardDTO> getBoardList(String pg) {
		//페이징처리는 여기서 함
		//1페이지당 5개씩
		int endNum = Integer.parseInt(pg)*5;
		int startNum = endNum - 4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		//List<BoardDTO> list = boardDAO.getBoardList(map);
		//return list; //밑에거랑 같은 말
		
		return boardDAO.getBoardList(map);
		
	}

	@Override
	public BoardDTO getBoard(String seq) { 
		//조회수 - 새로고침 방지
		return  boardDAO.getBoard(seq);
	}

	@Override
	public BoardPaging boardPaging(String pg) {
		int totalA = boardDAO.getTotalA(); //총글수
		
		boardPaging.setCurrentPage(Integer.parseInt(pg)); //현재 페이지
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}

	@Override
	public BoardPaging boardPaging(Map<String, String> map) {
		int totalA = boardDAO.getTotalSearchA(map); //검색한 총글수
		
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg"))); //현재 페이지, map에서 pg를 꺼낸다
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		return boardPaging;
	}
	
	@Override
	public void boardModify(Map<String, Object> map) {
		boardDAO.boardModify(map);
	}

	@Override
	public void boardReply(Map<String, String> map) { //pseq, pg, subject, content
		//원글
		BoardDTO pDTO = boardDAO.getBoard(map.get("pseq")); //원글번호
		
		map.put("id", (String) session.getAttribute("memId"));
		map.put("name", (String) session.getAttribute("memName"));
		map.put("email", (String) session.getAttribute("memEmail"));
		map.put("ref", pDTO.getRef()+""); //원글ref
		map.put("lev", pDTO.getLev()+""); //원글lev
		map.put("step", pDTO.getStep()+"");//원글step
		//여기서 +1 시키니까 Mapper의 sql이랑 충돌남
		
		boardDAO.boardReply(map);
	}

	@Override
	public void hitUpdate(String seq) {
		boardDAO.hitUpdate(seq);
	}

	@Override
	public void boardDelete(String seq) {
		boardDAO.boardDelete(seq);
		
	}

	@Override
	public List<BoardDTO> getBoardSearchList(Map<String, String> map) {
		//1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg"))*5; //map에서 pg를 꺼내 온다
		int startNum = endNum - 4;
		
		//pg, searchOption, keyword, startNum, endNum
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return boardDAO.getBoardSearchList(map); //map을 들고 가라
	}

}
