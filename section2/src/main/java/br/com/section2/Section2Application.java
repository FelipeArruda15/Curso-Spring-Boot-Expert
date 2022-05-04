package br.com.section2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Section2Application {

	@GetMapping("/hello")
	public String helloWorld(){
		return "Hello World!!";
	}

	public static void main(String[] args) {

		SpringApplication.run(Section2Application.class, args);
	}

}
