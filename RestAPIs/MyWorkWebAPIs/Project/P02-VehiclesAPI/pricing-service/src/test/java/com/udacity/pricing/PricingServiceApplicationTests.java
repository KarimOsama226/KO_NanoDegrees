package com.udacity.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest
@AutoConfigureMockMvc 

public class PricingServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

    @Test
	public void contextLoads() {

	}
	/*Here We should add a test */
	@Test
	public void testPricePage() throws Exception {
		mockMvc.perform(get("http://192.168.56.1:8082/prices"))
				.andExpect(status().isOk());
	}

}
