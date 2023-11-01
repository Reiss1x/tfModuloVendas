package com.pucrs.modulovendas.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.domain.PedidoDTO;
import com.pucrs.modulovendas.core.usecases.orcamentos.EfetivarOrcCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetAllOrcCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetOrcByCodCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetRelatorioCase;
import com.pucrs.modulovendas.core.usecases.pedidos.CriarPedidoCase;
import com.pucrs.modulovendas.core.usecases.pedidos.GetAllPedidosCase;



@RestController
public class VendasController {
    
    @Autowired
    private CriarPedidoCase criarPedido;
    @Autowired
    private GetAllPedidosCase getPedidos;
    @Autowired
    private GetOrcByCodCase getOrc;
    @Autowired
    private GetAllOrcCase getOrcs;
    @Autowired
    private EfetivarOrcCase efetivarOrc;
    @Autowired
    private GetRelatorioCase getRelatorio;

    //Criar pedidos
    @PostMapping("/home/pedidos/criar")
    public ResponseEntity<List<PedidoDTO>> postPedido(@RequestBody List<PedidoDTO> pedidos){
        return new ResponseEntity<List<PedidoDTO>>(criarPedido.execute(pedidos), HttpStatus.OK);
    }

    //visualizar pedidos
    @GetMapping("/admin/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos(){
        return new ResponseEntity<List<Pedido>>(getPedidos.execute(), HttpStatus.OK);
    }

    //solicitar orcamento específico (visualizar)
    @GetMapping("/admin/pedidos/{pedidoId}/solicitarOrcamento")
    public ResponseEntity<String> getOrcamento(@PathVariable Long pedidoId){
        return new ResponseEntity<String>(getOrc.execute(pedidoId).toString(), HttpStatus.OK);
    }

    //visualizar orcamentos não efetivados
    @GetMapping("/admin/orcamentos")
    public ResponseEntity<List<Orcamento>> getOrcamentos(){
        return new ResponseEntity<List<Orcamento>>(getOrcs.execute(), HttpStatus.OK);
    } 

    //efetuar compra
    @GetMapping("/admin/orcamentos/{orcamentoId}/efetivar")
    public ResponseEntity<String> efetivarOrcamento(@PathVariable Long orcamentoId){
        
        return new ResponseEntity<String>(efetivarOrc.execute(orcamentoId), HttpStatus.OK);
    }

    //get relatorios de orcamentos
    @GetMapping("/admin/relatorio/{num}")
    public ResponseEntity<List<Orcamento>> getRelatorio(@PathVariable int num){
        return new ResponseEntity<List<Orcamento>>(getRelatorio.execute().subList(0, num), HttpStatus.OK);
    }
}
