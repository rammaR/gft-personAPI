package com.rammar.me.personapi;

import com.rammar.me.personapi.controller.PersonController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonapiApplicationTests {

	@Autowired
	private PersonController personController;

	@Test
	void contextLoads() {
		assertThat(personController).isNotNull();
	}

}
