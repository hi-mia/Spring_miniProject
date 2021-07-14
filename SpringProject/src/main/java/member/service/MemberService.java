package member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public interface MemberService {

	public String login(Map<String, String> map, HttpSession session);

	public String checkId(String id);

	public List<ZipcodeDTO> checkPostSearch(Map<String, String> map);

	public void write(MemberDTO memberDTO);

	public void modify(MemberDTO memberDTO);

	public MemberDTO getMember(String id);

}
