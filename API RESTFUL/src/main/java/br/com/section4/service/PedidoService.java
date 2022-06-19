package br.com.section4.service;

import br.com.section4.domain.entity.Pedido;
import br.com.section4.domain.enums.StatusPedido;
import br.com.section4.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarPedido(Integer id, StatusPedido statusPedido);
}
