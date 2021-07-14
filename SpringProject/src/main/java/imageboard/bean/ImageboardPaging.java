package imageboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component //bean 등록
@Data
public class ImageboardPaging {
	private int currentPage; //현재페이지
	private int pageBlock; //[이전][1][2][3][다음] -> 총 글수에 영향을 받는다
	private int pageSize; //1페이지당 5개씩 -> 총 글수에 영향을 받는다
	private int totalA; //총글수
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		//pagingHTML이 null값을 갖고 있기 때문에 생성먼저
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+(pageSize-1)) / pageSize; //총페이지수 5
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1; //1
		
		int endPage = startPage + pageBlock -1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock)
			pagingHTML.append("<span id='paging' onclick='imageboardPaging("+(startPage-1)+")'>이전</span>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage) {//만약 현재 페이지라면 
				pagingHTML.append(" <span id='currentPaging' onclick='imageboardPaging("+i+")'>"+i+"</span> ");
			}else {
				pagingHTML.append(" <span id='paging' onclick='imageboardPaging("+i+")'>"+i+"</span> ");
			}
		}//for
		
		if(endPage < totalP)
			pagingHTML.append("<span id='paging' onclick='imageboardPaging("+(endPage+1)+")'>다음</span>");
	
	
	
	
	}
	
	
	
	
}
