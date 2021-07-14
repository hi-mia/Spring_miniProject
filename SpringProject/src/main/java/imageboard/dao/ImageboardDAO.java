package imageboard.dao;

import java.util.List;
import java.util.Map;

import imageboard.bean.ImageboardDTO;

public interface ImageboardDAO {

	public void imageboardWrite(ImageboardDTO imageboardDTO);

	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map);

	public ImageboardDTO getImageboard(String seq);

	public int getTotalA();

	public void imageboardDelete(Map<String, String[]> map);


}
