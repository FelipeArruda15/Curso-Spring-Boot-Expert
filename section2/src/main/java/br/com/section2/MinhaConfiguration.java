package br.com.section2;

import org.springframework.boot.*;
import org.springframework.context.annotation.*;

@Development
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Rodando a configuração de desenvolvimento");
        };
    }
}
