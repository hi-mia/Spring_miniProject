
package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value="member")
public class MemberController {
   @Autowired
   private MemberService memberService;
   
   @RequestMapping(value="login", method=RequestMethod.POST)
   @ResponseBody
   public String login(@RequestParam Map<String,String> map , HttpSession session) {

      return memberService.login(map,session);
   }
   
   @RequestMapping(value="logout", method=RequestMethod.GET)
   public String logout(HttpSession session) {
      session.invalidate();//무효화
      return "/index";
   }
   
   //모델 / 모델엔뷰 / 모델맵 셋 중 아무거나 / String으로 창만 뜨면 된다
   @RequestMapping(value="writeForm",method=RequestMethod.GET)
   public ModelAndView writeForm() {
      ModelAndView mav = new ModelAndView();
      mav.addObject("display","/member/writeForm.jsp");
      mav.setViewName("/index");//뷰의 위치로 가라고 말해주는거임,얘가 없으면 다시 돌아옴
      return mav;
   }
   
   @RequestMapping(value="write", method=RequestMethod.POST)
   @ResponseBody
   public void write(@ModelAttribute MemberDTO memberDTO) {
       memberService.write(memberDTO);
   }
   
   @RequestMapping(value="modifyForm",method=RequestMethod.GET)
   public String modifyForm(HttpSession session, Model model) {
	   String id = (String) session.getAttribute("memId");
	   MemberDTO memberDTO = memberService.getMember(id);
	   
	   model.addAttribute("memberDTO", memberDTO);
	   model.addAttribute("display", "/member/modifyForm.jsp");
	   return "/index";
   }
   
   //모델, 모델앤뷰 잡든 상관 없다
   @RequestMapping(value="modify", method=RequestMethod.POST)
   //@ResponseBody //ajax 안 써서 필요 없다
   public String modify(@ModelAttribute MemberDTO memberDTO, Model model) { 
	   memberService.modify(memberDTO);
	   
	   model.addAttribute("display", "/member/modify.jsp");
	   return "/index"; //index 가야해서 string으로 바꿈
   }
   
   @RequestMapping(value="checkId",method=RequestMethod.POST)
   public @ResponseBody String checkId(@RequestParam String id) {//뷰로 못가게 해서 순수한 문자열로 받도록 한다
	   return memberService.checkId(id);
   }
   
   @RequestMapping(value="checkPost",method=RequestMethod.GET)
   public String checkPost() {
	   return "/member/checkPost";
   }
   
   @RequestMapping(value="checkPostSearch",method=RequestMethod.POST)
   public ModelAndView checkPostSearch(@RequestParam Map<String, String> map) {
	   List<ZipcodeDTO> list = memberService.checkPostSearch(map);
	
	   ModelAndView mav = new ModelAndView();
	   mav.addObject("list", list);
	   mav.setViewName("jsonView");
	   return mav;
   }
   
   /* 
   //모델 사용 
   @RequestMapping(value="writeForm",method=RequestMethod.GET)
   public String writeForm(Model model) {
	   model.addAttribute("display", "/member/writeForm.jsp");
	   return "/index";
   }
   */
   
//이렇게 하면 아예 새창이 떠버린다
//   @RequestMapping(value="writeForm",method=RequestMethod.GET)
//   public String writeForm(HttpSession session) {
//      session.setAttribute("display","/spring/member/writeFrom");
//      return "/index";
//   }
}