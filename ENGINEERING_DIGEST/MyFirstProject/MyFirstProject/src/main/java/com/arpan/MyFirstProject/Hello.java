package com.arpan.MyFirstProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

	@GetMapping("/abc")
	public String sayHello() {
		return "Hello!!";
	}
}
