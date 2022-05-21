package br.com.section4.service;

import br.com.section4.domain.entity.Pedido;
import br.com.section4.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar( PedidoDTO dto);
}
