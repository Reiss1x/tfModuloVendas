package com.pucrs.modulovendas.domain;

import java.util.List;

import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;

public interface IOrcamentoRepository {
    public Orcamento postOrcamento(Orcamento orcamento);
    public List<Orcamento> getOrcamentos();
    public Orcamento getOrcamentoById(int orcamentoId);
    public Pedido postPedido(Pedido ped);
    public List<Pedido> getPedidos();
    public Pedido getPedidoById(int pedidoId);
}
