package com.pucrs.modulovendas.presenter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetAllOrcCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetCompradoresCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GetGastadoresCase;
import com.pucrs.modulovendas.core.usecases.pedidos.GetAllPedidosCase;

@RestController
public class ConsultasController {
    @Autowired
    private GetAllPedidosCase getPedidos;
    @Autowired
    private GetAllOrcCase getOrcs;
    @Autowired
    private GetCompradoresCase getCompradores;
    @Autowired
    private GetGastadoresCase getGastadores;
    
    //Retorna um mapa de compradores/compras
    @GetMapping("/admin/relatorio/compradores")
    public ResponseEntity<Map<String, Long>> getCompradoresRelatorio(){
        return new ResponseEntity<Map<String, Long>>(getCompradores.execute(), HttpStatus.OK);
    }
    //Retorna um mapa de compradores/gastos
    @GetMapping("/admin/relatorio/gastadores")
    public ResponseEntity<Map<String, Double>> getGastadoresRelatorio(){
        return new ResponseEntity<Map<String, Double>>(getGastadores.execute(), HttpStatus.OK);
    }
    //retorna lista de produtos ordenados por preço
    @GetMapping("/admin/relatorio/produtos")
    public ResponseEntity<Map<String, Double>> getProdutosRelatorio(){
        return new ResponseEntity<Map<String, Double>>(getGastadores.execute(), HttpStatus.OK);
    }
     //visualizar orcamentos não efetivados
    @GetMapping("/admin/orcamentos")
    public ResponseEntity<List<Orcamento>> getOrcamentos(){
        return new ResponseEntity<List<Orcamento>>(getOrcs.execute(), HttpStatus.OK);
    } 
    //visualizar pedidos
    @GetMapping("/admin/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos(){
        return new ResponseEntity<List<Pedido>>(getPedidos.execute(), HttpStatus.OK);
    }
}
