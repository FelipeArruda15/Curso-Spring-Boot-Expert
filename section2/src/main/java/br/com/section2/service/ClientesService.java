package br.com.section2.service;

import br.com.section2.model.*;
import br.com.section2.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ClientesService {

    private ClientesRepository repository;

    public ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
    }

    public void validarCliente(Cliente cliente){

    }
}
