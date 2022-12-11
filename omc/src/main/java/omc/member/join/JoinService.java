package omc.member.join;

import java.util.Map;

public interface JoinService {

	int selectMemberIdCk(String id) throws Exception;

	void insertMember(Map<String, Object> map) throws Exception;

}
