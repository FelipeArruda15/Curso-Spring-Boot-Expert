package br.com.section4.service.impl;


import br.com.section4.domain.entity.Cliente;
import br.com.section4.domain.entity.ItemPedido;
import br.com.section4.domain.entity.Pedido;
import br.com.section4.domain.entity.Produto;
import br.com.section4.domain.enums.StatusPedido;
import br.com.section4.exception.PedidoNaoEncontradoException;
import br.com.section4.exception.RegraNegocioException;
import br.com.section4.repository.Clientes;
import br.com.section4.repository.ItensPedido;
import br.com.section4.repository.Pedidos;
import br.com.section4.repository.Produtos;
import br.com.section4.rest.dto.ItemPedidoDTO;
import br.com.section4.rest.dto.PedidoDTO;
import br.com.section4.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes repositoryClientes;
    private final Produtos repositoryProdutos;
    private final ItensPedido repositoryItens;

    public PedidoServiceImpl(Pedidos repository, Clientes repositoryClientes, Produtos repositoryProdutos, ItensPedido repositoryItens) {
        this.repository = repository;
        this.repositoryClientes = repositoryClientes;
        this.repositoryProdutos = repositoryProdutos;
        this.repositoryItens = repositoryItens;
    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = repositoryClientes
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente invalido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> items = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        repositoryItens.saveAll(items);
        pedido.setItens(items);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizarPedido(Integer id, StatusPedido statusPedido) {
        repository.findById(id).map(pedido -> {
            pedido.setStatus(statusPedido);
            return repository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items.");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = repositoryProdutos
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de Produto invalido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setProduto(produto);
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
