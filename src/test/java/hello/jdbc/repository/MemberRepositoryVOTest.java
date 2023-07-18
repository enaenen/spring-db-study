package hello.jdbc.repository;

import static org.junit.jupiter.api.Assertions.*;

import hello.jdbc.domain.Member;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class MemberRepositoryVOTest {

	MemberRepositoryVO repository = new MemberRepositoryVO();

	@Test
	void crud() throws SQLException {
		Member member = new Member("memberV0", 10000);
		repository.save(member);
	}

}