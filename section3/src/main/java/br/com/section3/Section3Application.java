package br.com.section3;

import br.com.section3.domain.entity.Cliente;
import br.com.section3.domain.entity.Pedido;
import br.com.section3.repository.Clientes;
import br.com.section3.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Section3Application {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
        return args -> {
            System.out.println("Salvando Clientes:");
            Cliente cliente1 = new Cliente("Felipe");
            clientes.save(cliente1);

            Pedido pedido1 = new Pedido();
            pedido1.setCliente(cliente1);
            pedido1.setDataPedido(LocalDate.now());
            pedido1.setTotal(BigDecimal.valueOf(100));
            pedidos.save(pedido1);

//            Cliente cliente = clientes.findClienteFetchPedidos(cliente1.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            List<Pedido> result = pedidos.findByCliente(cliente1);
            result.forEach(System.out::println);

        };

    }

    public static void main(String[] args) {
        SpringApplication.run(Section3Application.class, args);
    }

}
