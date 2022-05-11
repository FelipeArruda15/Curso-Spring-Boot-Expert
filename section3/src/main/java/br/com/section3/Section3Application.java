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

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes:");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado.");
                clientes.save(c);
            });

            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Obtendo Clientes por nome:");
            clientes.findByNomeLike("Fel").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            todosClientes.forEach(c -> {
                clientes.deleteById(c.getId());
            });

            todosClientes = clientes.findAll();
            System.out.println(todosClientes.size());
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(Section3Application.class, args);
    }

}
