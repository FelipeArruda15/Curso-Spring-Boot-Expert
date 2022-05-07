package br.com.section3;

import br.com.section3.domain.entity.Cliente;
import br.com.section3.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Section3Application {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			clientes.salvar(new Cliente("Felipe"));
			clientes.salvar(new Cliente("Daniela"));
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Section3Application.class, args);
	}

}
