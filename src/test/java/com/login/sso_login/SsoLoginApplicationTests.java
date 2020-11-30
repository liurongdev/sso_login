package com.login.sso_login;

import com.login.sso_login.utils.SecretUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class SsoLoginApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testSecret(){
		String name = "ronge";
		String encryptName = SecretUtils.AesEncrypt(name);
		System.out.println(encryptName);
		String origin_name = SecretUtils.AesDecrypt(encryptName);
		System.out.println(origin_name);
	}

}
