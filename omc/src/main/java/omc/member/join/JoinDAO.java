package omc.member.join;

import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("joinDAO")
public class JoinDAO extends AbstractDAO {

	public int selectMemberIdCk(String id) throws Exception {
		return (Integer) selectOne("member.selectMemberIdCk", id);
	}

	public void insertMember(Map<String, Object> map) throws Exception {
		insert("member.insertMember", map);
	}

}
