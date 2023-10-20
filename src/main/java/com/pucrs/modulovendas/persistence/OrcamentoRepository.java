package com.pucrs.modulovendas.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.domain.IOrcamentoRepository;
import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;

@Repository
public class OrcamentoRepository implements IOrcamentoRepository{
    private ArrayList<Orcamento> orcamentos = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    
    @Override
    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    @Override
    public Orcamento getOrcamentoById(int orcamentoId){
        Optional<Orcamento> orc = orcamentos.stream().filter(p -> p.getId() == orcamentoId).findFirst();
        if(orc.isPresent()){
            return orc.get();
        }
        throw new IllegalArgumentException("Orcamento não existe");
    }
    
    @Override
    public Orcamento postOrcamento(Orcamento orcamento) {
        orcamentos.add(orcamento);
        return orcamento;
    }
    @Override
    public Pedido postPedido(Pedido ped){
        pedidos.add(ped);  
        return ped;
    }
    @Override
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    @Override
    public Pedido getPedidoById(int pedidoId){
        Optional<Pedido> pedido = pedidos.stream().filter(p -> p.getId() == pedidoId).findFirst();
        if(pedido.isPresent()){
            return pedido.get();
        }
        throw new IllegalArgumentException("Orcamento não existe");
    }
}
