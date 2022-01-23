package com.lostoctet.restservices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "SpringBoot Demo API",
				version = "v1.0",
				description = "" + "Demo Application for Learning SpringBoot v2.6.1",
				contact = @Contact(name = "LostOctet", url = "https://springboot.io", email = "springboot-demo@gmail.com"),
				license = @License(name = "Open Licence", url = "https://github.com/lost-octet/springboot-demo.git")),
		servers = @Server(url = "http://localhost:8080")
)

//This Annotation is used for adding an authentication to the API Documentation
//@SecurityScheme(
//		name = "api",
//		scheme = "basic",
//		type = SecuritySchemeType.HTTP,
//		in = SecuritySchemeIn.HEADER)

public class SpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

}
