package com.lostoctet.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//Simple Method, URI= /helloworld, HTTP method= GET
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "New Hello World";
	}
	
	//Method returning a Bean
	@GetMapping("/hello-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails ("Atul", "Pandey", "Faizabad");
	}
	
}
