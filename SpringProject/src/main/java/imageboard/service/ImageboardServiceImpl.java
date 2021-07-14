package imageboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

@Service
public class ImageboardServiceImpl implements ImageboardService{
	@Autowired
	private ImageboardDAO imageboardDAO;
	@Autowired
	private ImageboardPaging imageboardPaging;

	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		imageboardDAO.imageboardWrite(imageboardDTO); //DTO들고 가라
	}

	@Override
	public List<ImageboardDTO> getImageboardList(String pg) {
		//1페이지당 3개씩
		int endNum = Integer.parseInt(pg)*3;
		int startNum = endNum-2;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum",startNum);
		map.put("endNum",endNum);
		
		return imageboardDAO.getImageboardList(map);
	}

	@Override
	public ImageboardDTO getImageboard(String seq) {
		return imageboardDAO.getImageboard(seq);
	}

	@Override
	public ImageboardPaging imageboardPaging(String pg) {
		int totalA = imageboardDAO.getTotalA();//총글수
		
		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		imageboardPaging.makePagingHTML();
		
		return imageboardPaging;
	}

	@Override
	public void imageboardDelete(String[] check) {
		//mapper보면 map에다 실어줘야지 for문이 돌아감
		Map<String, String[]> map = new HashMap<String, String[]>(); //string, string배열로 값이 넘어온다
		map.put("array", check);
		
		imageboardDAO.imageboardDelete(map);
	}
}
