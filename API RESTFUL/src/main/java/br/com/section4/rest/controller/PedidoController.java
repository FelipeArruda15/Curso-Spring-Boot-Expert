package br.com.section4.rest.controller;

import br.com.section4.domain.enums.entity.ItemPedido;
import br.com.section4.domain.enums.entity.Pedido;
import br.com.section4.domain.enums.StatusPedido;
import br.com.section4.rest.dto.AtualizacaoStatusPedidoDTO;
import br.com.section4.rest.dto.InformacaoItemPedidoDTO;
import br.com.section4.rest.dto.InformacoesPedidoDTO;
import br.com.section4.rest.dto.PedidoDTO;
import br.com.section4.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map(pedido -> converter(pedido))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizarPedido(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        InformacoesPedidoDTO dto = new InformacoesPedidoDTO();
        dto.setCodigo(pedido.getId());
        dto.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dto.setCpf(pedido.getCliente().getCpf());
        dto.setNomeCliente(pedido.getCliente().getNome());
        dto.setTotal(pedido.getTotal());
        dto.setStatus(pedido.getStatus().name());
        dto.setItems(converterItems(pedido.getItens()));
        return dto;
    }

    private List<InformacaoItemPedidoDTO> converterItems(List<ItemPedido> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(itemPedido -> {
            InformacaoItemPedidoDTO item = new InformacaoItemPedidoDTO();
            item.setQuantidade(itemPedido.getQuantidade());
            item.setDescricaoProduto(itemPedido.getProduto().getDescricao());
            item.setPrecoUnitario(itemPedido.getProduto().getPreco());
            return item;
        }).collect(Collectors.toList());
    }
}
