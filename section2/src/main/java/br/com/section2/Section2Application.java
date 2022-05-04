package br.com.section2;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Section2Application {

	@Value("${application.name}")
	private String applicationName;

	@Cachorro
	private Animal animal;

	@Bean(name = "executarAnimal")
	public CommandLineRunner executar(){
		return args -> {
			this.animal.fazerBarulho();
		};
	}

	@GetMapping("/hello")
	public String helloWorld(){
		return applicationName;
	}

	public static void main(String[] args) {

		SpringApplication.run(Section2Application.class, args);
	}

}
