package com.lostoctet.restservices.Hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {


	@Autowired
	ResourceBundleMessageSource messageSource;

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

	//Method for I18n i.e. Internationalization
	@GetMapping("/hello-int")
	public String getMessagesI18n(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}

	@GetMapping("/hello-int2")
	public String getMessagesI18n2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
	
}
