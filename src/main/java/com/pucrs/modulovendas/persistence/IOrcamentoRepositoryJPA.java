package com.pucrs.modulovendas.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;

public interface IOrcamentoRepositoryJPA extends CrudRepository<Orcamento, Long> {
    List<Orcamento> findAllOrcamento();
    Optional<Orcamento> findOrcamentoBycod(Long cod);
    List<Pedido> findAllPedido();
    Optional<Pedido> findPedidoBycod(Long cod);




    // public Orcamento postOrcamento(Orcamento orcamento);
    // public List<Orcamento> getOrcamentos();
    // public Orcamento getOrcamentoById(int orcamentoId);
    // public Pedido postPedido(Pedido ped);
    // public List<Pedido> getPedidos();
    // public Pedido getPedidoById(int pedidoId);
}
