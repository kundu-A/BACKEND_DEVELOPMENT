package com.arpan.login.OTPLogin;

import com.arpan.login.OTPLogin.service.RedisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OtpLoginApplication {

	public static void main(String[] args) {
		//SpringApplication.run(OtpLoginApplication.class, args);
		ConfigurableApplicationContext context=SpringApplication.run(OtpLoginApplication.class, args);
		RedisTest bean = context.getBean(RedisTest.class);
		System.out.println("Hiii");
		bean.redisTest();
	}

}
