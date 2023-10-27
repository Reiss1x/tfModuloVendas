package com.pucrs.modulovendas.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.domain.VendasService;
import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;


@RestController
public class VendasController {
    @Autowired
    private VendasService vs;

    //Criar pedidos
    @PostMapping("/home/pedidos/criar")
    public List<Pedido> postPedido(@RequestBody List<Pedido> pedidos){
        for(Pedido p : pedidos){
            vs.postPedido(p);
        }
        return pedidos;
    }

    //visualizar pedidos
    @GetMapping("/admin/pedidos")
    public List<Pedido> getPedidos(){
        return vs.getPedidos();
    }

    //solicitar orcamento específico (visualizar)
    @GetMapping("/admin/pedidos/{pedidoId}/solicitarOrcamento")
    public String postSolicitarOrcamento(@PathVariable Long pedidoId){
        return vs.getOrcamento(pedidoId).toString();
    }

    //visualizar orcamentos não efetivados
    @GetMapping("/admin/orcamentos")
    public List<Orcamento> getOrcamentos(){
        return vs.getOrcamentos();
    } 

    //efetuar compra
    @GetMapping("/admin/orcamentos/{orcamentoId}/efetivar")
    public Orcamento efetivarOrcamento(@PathVariable Long orcamentoId){
        return vs.efetivarOrcamento(orcamentoId);
    }

    //get relatorios
    @GetMapping("/admin/relatorio")
    public List<Orcamento> getRelatorio(@RequestParam int num){
        return vs.getRelatorio().subList(0, num);
    }
}
