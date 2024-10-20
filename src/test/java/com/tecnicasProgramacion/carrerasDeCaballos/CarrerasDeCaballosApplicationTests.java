package com.tecnicasProgramacion.carrerasDeCaballos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarrerasDeCaballosApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
		System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));

	}

}
