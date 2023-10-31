package com.pucrs.modulovendas.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.domain.VendasService;
import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;
import com.pucrs.modulovendas.entities.PedidoDTO;


@RestController
public class VendasController {
    @Autowired
    private VendasService vs;

    //Criar pedidos
    @PostMapping("/home/pedidos/criar")
    public ResponseEntity<List<PedidoDTO>> postPedido(@RequestBody List<PedidoDTO> pedidos){
        for(PedidoDTO p : pedidos){
            vs.postPedido(p);
        }
        return new ResponseEntity<List<PedidoDTO>>(pedidos, HttpStatus.OK);
    }

    //visualizar pedidos
    @GetMapping("/admin/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos(){
        return new ResponseEntity<List<Pedido>>(vs.getPedidos(), HttpStatus.OK);
    }

    //solicitar orcamento específico (visualizar)
    @GetMapping("/admin/pedidos/{pedidoId}/solicitarOrcamento")
    public ResponseEntity<String> postSolicitarOrcamento(@PathVariable Long pedidoId){
        return new ResponseEntity<String>(vs.getOrcamento(pedidoId).toString(), HttpStatus.OK);
    }

    //visualizar orcamentos não efetivados
    @GetMapping("/admin/orcamentos")
    public ResponseEntity<List<Orcamento>> getOrcamentos(){
        return new ResponseEntity<List<Orcamento>>(vs.getOrcamentos(), HttpStatus.OK);
    } 

    //efetuar compra
    @GetMapping("/admin/orcamentos/{orcamentoId}/efetivar")
    public ResponseEntity<String> efetivarOrcamento(@PathVariable Long orcamentoId){
        vs.efetivarOrcamento(orcamentoId);
        return new ResponseEntity<String>("Orcamento Efetivado.", HttpStatus.OK);
    }

    //get relatorios de orcamentos
    @GetMapping("/admin/relatorio")
    public ResponseEntity<List<Orcamento>> getRelatorio(@RequestParam int num){
        return new ResponseEntity<List<Orcamento>>(vs.getRelatorio().subList(0, num), HttpStatus.OK);
    }
}
