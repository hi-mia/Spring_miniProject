package member.dao;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public interface MemberDAO {

	public MemberDTO login(Map<String, String> map);

	public MemberDTO checkId(String id);

	public List<ZipcodeDTO> checkPostSearch(Map<String, String> map);

	public void write(MemberDTO memberDTO);

	public MemberDTO getMember(String id);

	public void modify(MemberDTO memberDTO);


}
