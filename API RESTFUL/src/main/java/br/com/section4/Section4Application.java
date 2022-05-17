package br.com.section4;

import br.com.section4.domain.entity.Cliente;
import br.com.section4.domain.entity.Pedido;
import br.com.section4.repository.Clientes;
import br.com.section4.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Section4Application {

    @Bean
    public  CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
       return args -> {
           Cliente c = new Cliente(null, "Felipe");
           clientes.save(c);
       };
    }

    public static void main(String[] args) {
        SpringApplication.run(Section4Application.class, args);
    }

}
