package imageboard.service;

import java.util.List;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

public interface ImageboardService {

	public void imageboardWrite(ImageboardDTO imageboardDTO);

	public List<ImageboardDTO> getImageboardList(String pg);

	public ImageboardDTO getImageboard(String seq);

	public ImageboardPaging imageboardPaging(String pg);

	public void imageboardDelete(String[] check);
}
