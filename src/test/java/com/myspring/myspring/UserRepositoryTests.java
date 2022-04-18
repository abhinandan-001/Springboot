package com.myspring.myspring;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		user user = new user();
		user.setEmail("a@gmail.com");
		user.setPassword("ab022");
		user.setFirstname("yttr");
		user.setLastname("H");
		
		user savedUser = repo.save(user);
		
		user existUser = entityManager.find(user.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}

	
	@Test
	public void testFindUserByEmail() {
		String email = "nam1@gmail.com";
		
		user user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
}
