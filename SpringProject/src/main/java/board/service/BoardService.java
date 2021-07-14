package board.service;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;
import board.bean.BoardPaging;

public interface BoardService {

	public void boardWrite(Map<String, String> map);

	public List<BoardDTO> getBoardList(String pg);

	public BoardDTO getBoard(String seq);

	public BoardPaging boardPaging(String pg);

	public BoardPaging boardPaging(Map<String, String> map);

	public void boardModify(Map<String, Object> map);

	public void boardReply(Map<String, String> map);

	public void hitUpdate(String seq);

	public void boardDelete(String seq);

	public List<BoardDTO> getBoardSearchList(Map<String, String> map);



	
}
