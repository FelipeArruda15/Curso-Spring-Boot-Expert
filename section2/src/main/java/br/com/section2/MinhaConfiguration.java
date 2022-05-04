package br.com.section2;

import org.springframework.context.annotation.*;

@Configuration
public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }
}
