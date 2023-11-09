package com.pucrs.modulovendas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.pucrs.modulovendas.core.domain.Item;
import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.orcamentos.ChecarDescontoCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.CriarOrcamentoCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;
import com.pucrs.modulovendas.core.usecases.orcamentos.OrcamentoRepo;
import com.pucrs.modulovendas.core.usecases.orcamentos.SetValidadeCase;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ModuloVendasApplicationTests {

	 @Mock
    private Produto produto;

    @Mock
    private Pedido pedido;

	 @Mock
    private OrcamentoRepo orcamentoRepo;

    @Mock
    private GalpaoRepo galpaoRepo;

    @Mock
    private ChecarDescontoCase checarDescontoCase;

    @Mock
    private SetValidadeCase setValidadeCase;

    @InjectMocks
    private CriarOrcamentoCase criarOrcamentoCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
     public static void setUpBeforeAll() {
    }

    @Test
    public void testProduto() {
        Mockito.when(produto.getDesc()).thenReturn("Produto de Teste");
        Mockito.when(produto.getPreco()).thenReturn(10.0);
        Mockito.when(produto.getQntMin()).thenReturn(5);
        Mockito.when(produto.getQntMax()).thenReturn(20);
        Mockito.when(produto.getQnt()).thenReturn(15);

        assertEquals("Produto de Teste", produto.getDesc());
        assertEquals(10.0, produto.getPreco(), 0.01);
        assertEquals(5, produto.getQntMin());
        assertEquals(20, produto.getQntMax());
        assertEquals(15, produto.getQnt());
    }

    @Test
    public void testPedido() {
        Mockito.when(pedido.getNomeCliente()).thenReturn("Cliente de Teste");
        Mockito.when(pedido.getListaProd()).thenReturn(new ArrayList<>());

        assertEquals("Cliente de Teste", pedido.getNomeCliente());
        assertTrue(pedido.getListaProd().isEmpty());
    }

}
