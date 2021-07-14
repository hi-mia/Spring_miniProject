package imageboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value="imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;

	@RequestMapping(value="imageboardWriteForm", method=RequestMethod.GET)
	public String imageboardWriteForm(Model model) {
		
		model.addAttribute("display", "/imageboard/imageboardWriteForm.jsp");
		return "/index";
	}
	
//name="img" 1개인 경우
//	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								@RequestParam MultipartFile img) {
//		
//		String filePath = "D:\\Spring\\workspace\\SpringProject\\src\\main\\webapp\\storage";
//		String fileName = img.getOriginalFilename();
//		File file = new File(filePath, fileName);//파일 생성
//		
//		//파일 복사
//		try {
//			
//			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
//		
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		imageboardDTO.setImage1(fileName);
//		
//	}
	
//------------------------------
//	//name="img" 2개 이상인 경우
//	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								@RequestParam MultipartFile[] img) { //2개 이상이면 배열로 들어온다
//		
//		String filePath = "D:\\Spring\\workspace\\SpringProject\\src\\main\\webapp\\storage";
//		String fileName;
//		File file;
//		
//		//파일 복사
//		if(img[0] != null) {
//			fileName = img[0].getOriginalFilename();
//			file = new File(filePath, fileName);
//			try {
//				FileCopyUtils.copy(img[0].getInputStream(), new FileOutputStream(file));
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//			imageboardDTO.setImage1(fileName);	
//		}else {
//			imageboardDTO.setImage1("");
//			
//		}
//		//------------------------------------------- 2개밖에 없어서 for문 안 돌리고 직접 숫자 줌
//		if(img[1] != null) {
//			fileName = img[1].getOriginalFilename();
//			file = new File(filePath, fileName);
//			try {
//				FileCopyUtils.copy(img[1].getInputStream(), new FileOutputStream(file));
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//			imageboardDTO.setImage2(fileName); //디비에는 못 들어간다
//		}else {
//			imageboardDTO.setImage2("");
//			
//		}
//	}
	
	//----------------------
	//한 번에 여러 개의 파일을 선택했을 때
	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam("img[]") List<MultipartFile> list) { //이미지 배열타입을 리스트로 받음
		
		String filePath = "D:\\Spring\\workspace\\SpringProject\\src\\main\\webapp\\storage";
		String fileName;
		File file;
		
		for(MultipartFile img : list) {
			fileName = img.getOriginalFilename();
			file = new File(filePath, fileName);
			
			//파일 복사
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");
		
			//DB
			imageboardService.imageboardWrite(imageboardDTO); //함수 이름과 똑같이 imageboardWrite라고 함+dto들고 감
		
			//jsp로 가서 본다
			
		} //for
	}
	
	@RequestMapping(value="imageboardList", method=RequestMethod.GET)
	public String imageboardList(@RequestParam(required=false, defaultValue="1") String pg, 
							Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		
		return "/index";
	}
	//model도 싣는다
	//pg가 올 때도 있고 안 올때도 있으니 required=false + 무조건 1페이지로 가자 / 안들어오면 1페이지로 인식해라
	
	@RequestMapping(value="getImageboardList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getImageboardList(@RequestParam String pg) { //저 쪽에서 pg값을 넘겨줌 / 여기서 required는 써도그만 안써도 그만
		
		//1페이지당 3개씩
		List<ImageboardDTO> list = imageboardService.getImageboardList(pg);
		
		//페이징 처리
		ImageboardPaging imageboardPaging = imageboardService.imageboardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("imageboardPaging", imageboardPaging); //페이지 실어감
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="imageboardView", method=RequestMethod.GET)
	public String boardView(@RequestParam String seq, //나에게 들어오는 데이터: seq, pg
							@RequestParam String pg,
							Model model) { //데이터 2개를 나에게 준다

		model.addAttribute("seq", seq); //seq를 모델에 넣어줌
		model.addAttribute("pg", pg);
		
		model.addAttribute("display", "/imageboard/imageboardView.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getImageboardView", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getImageboardView(@RequestParam String seq) { //데이터 2개를 나에게 준다
		ImageboardDTO imageboardDTO = imageboardService.getImageboard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("imageboardDTO",imageboardDTO);
		mav.setViewName("jsonView");
		return mav;
		
	}
	
	@RequestMapping(value="imageboardDelete", method=RequestMethod.GET) //저쪽이 GET방식이라서 GET을 쓰는거.. 일치시켜주자
	public ModelAndView imageboardDelete(String[] check) { //배열로 넘어온다(선택한 값이 몇 개? 복수임)
		imageboardService.imageboardDelete(check);
		
		return new ModelAndView("redirect:/imageboard/imageboardList"); //삭제하면 무조건 1페이지 간다
	}
	
}