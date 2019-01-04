package com.projectmanagerapp.test;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.projectmanagerapp.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectManagerAppBootApplicationTests 
{
	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	@Ignore
	public void getUserDetails() 
	{
		ResponseEntity<List<Users>> response = testRestTemplate.exchange(
				  "http://localhost:8181/ProjectManagerApp/users",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Users>>(){});
				List<Users> users = response.getBody();
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(users.size()).isNotEqualTo(0);
	}
}



