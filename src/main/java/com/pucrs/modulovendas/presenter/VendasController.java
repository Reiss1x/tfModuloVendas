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
import com.pucrs.modulovendas.core.domain.PedidoDTO;
import com.pucrs.modulovendas.core.usecases.orcamentos.EfetivarAllOrcs;
import com.pucrs.modulovendas.core.usecases.orcamentos.EfetivarOrcCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetOrcByCodCase;
import com.pucrs.modulovendas.core.usecases.pedidos.CriarPedidoCase;




@RestController
public class VendasController {
    
    @Autowired
    private CriarPedidoCase criarPedido;
    
    @Autowired
    private GetOrcByCodCase getOrc;
    
    @Autowired
    private EfetivarOrcCase efetivarOrc;
    
    @Autowired
    private EfetivarAllOrcs efetivarOrcs;
    
    //Criar pedidos
    @PostMapping("/home/pedidos/criar")
    public ResponseEntity<List<PedidoDTO>> postPedido(@RequestBody List<PedidoDTO> pedidos){
        return new ResponseEntity<List<PedidoDTO>>(criarPedido.execute(pedidos), HttpStatus.OK);
    }

    //solicitar orcamento específico (visualizar)
    @GetMapping("/admin/pedidos/{pedidoId}/solicitarOrcamento")
    public ResponseEntity<String> getOrcamento(@PathVariable Long pedidoId){
        return new ResponseEntity<String>(getOrc.execute(pedidoId).toString(), HttpStatus.OK);
    }

    //efetuar orcamento especifico
    @GetMapping("/admin/orcamentos/{orcamentoId}/efetivar")
    public ResponseEntity<String> efetivarOrcamento(@PathVariable Long orcamentoId){
        
        return new ResponseEntity<String>(efetivarOrc.execute(orcamentoId), HttpStatus.OK);
    }

    //efetivar todos orcamentos
    @GetMapping("/admin/orcamentos/efetivar")
    public ResponseEntity<String> efetivarAllOrcamentos(){
        return new ResponseEntity<String>(efetivarOrcs.execute(), HttpStatus.OK);
    }

}
