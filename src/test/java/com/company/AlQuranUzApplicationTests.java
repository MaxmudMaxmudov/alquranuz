package com.company;

import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlQuranUzApplicationTests {

	@Autowired
	private ProfileService profileService;
	@Test
	void createProfile() {

		ProfileDTO dto =  new ProfileDTO();

		dto.setName("Ali");
		dto.setSurname("Aliyev");
		dto.setLogin("ali123");
		dto.setPassword("1234");
		dto.setEmail("ali@gmail.com");
		dto.setRole(ProfileRole.USER_ROLE);
		dto.setStatus(ProfileStatus.CREATED);


		profileService.create(dto);

	}

}
