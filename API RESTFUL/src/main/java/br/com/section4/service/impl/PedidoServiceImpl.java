package br.com.section4.service.impl;

import br.com.section4.repository.Pedidos;
import br.com.section4.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
