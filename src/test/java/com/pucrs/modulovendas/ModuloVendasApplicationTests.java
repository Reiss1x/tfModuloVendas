package com.pucrs.modulovendas;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.usecases.orcamentos.OrcamentoRepo;
import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.CalculaDescontoCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.DezCompras;
import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.TresCompras;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ModuloVendasApplicationTests {
   @Mock
    private DezCompras dezCompras;
    @Mock
    private TresCompras tresCompras;
    @Mock
    private OrcamentoRepo orcamentoRepo;
    @InjectMocks
    private CalculaDescontoCase calculaDescontoCase;

    ModuloVendasApplicationTests() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void calculaDesconto_ClienteComDezComprasDescontoZero() {
        Mockito.when(this.dezCompras.calcula("clienteName")).thenReturn(0.0);
        Mockito.when(this.tresCompras.calcula("clienteName")).thenReturn(0.15);
        double desconto = this.calculaDescontoCase.calcula("clienteName");
        Assertions.assertEquals(0.15, desconto);
    }

    @Test
    void calculaDesconto_ClienteComUmaCompraSuperiorA10Mil() {
        Mockito.when(dezCompras.calcula("clienteName")).thenReturn(0.0);
        Mockito.when(tresCompras.calcula("clienteName")).thenReturn(0.0);
        Orcamento compraSuperiorA10Mil = new Orcamento();
        Mockito.when(orcamentoRepo.findBynomeCliente("clienteName"))
                .thenReturn(Collections.singletonList(compraSuperiorA10Mil));

        double desconto = calculaDescontoCase.calcula("clienteName");
        System.out.println("Desconto calculado: " + desconto); // Adiciona um log para verificar o desconto calculado
        Assertions.assertEquals(0.10, desconto, 0.1); // Desconto de 10% para compras superiores a 10 mil
    }

    @Test
    void calculaDesconto_ClienteComUmaCompraInferiorA10Mil() {
        Mockito.when(this.dezCompras.calcula("clienteName")).thenReturn(0.0);
        Mockito.when(this.tresCompras.calcula("clienteName")).thenReturn(0.0);

        // Simula uma compra inferior a 10 mil
        Mockito.when(this.orcamentoRepo.findBynomeCliente("clienteName"))
                .thenReturn(Collections.singletonList(new Orcamento()));

        double desconto = this.calculaDescontoCase.calcula("clienteName");
        Assertions.assertEquals(0.0, desconto);
    }
}