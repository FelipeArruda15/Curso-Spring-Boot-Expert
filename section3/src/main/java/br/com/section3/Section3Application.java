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
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando Clientes:");
            clientes.save(new Cliente("Felipe"));
            clientes.save(new Cliente("Daniela"));

            boolean existe = clientes.existsByNome("Felipe");
            System.out.println("Existe algum cliente com o nome Felipe? R=" + existe);
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(Section3Application.class, args);
    }

}
