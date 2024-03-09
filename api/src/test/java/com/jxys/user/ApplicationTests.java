package com.jxys.user;


import com.jxys.user.service.impl.GaodeLocationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Resource
	private GaodeLocationServiceImpl gaodeLocationService;

//	@Test
//	public void contextLoads() {
//	}


	@Test
	public void testReverseLocation(){

		gaodeLocationService.houseNameToLngLat();

	}

}
