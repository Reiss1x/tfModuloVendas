package com.pucrs.modulovendas;

import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.CalculaDescontoCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.DezCompras;
import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.TresCompras;
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
   @InjectMocks
   private CalculaDescontoCase calculaDescontoCase;

   ModuloVendasApplicationTests() {
   }

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   void calculaDesconto() {
      Mockito.when(this.dezCompras.calcula("clienteName")).thenReturn(0.15);
      Mockito.when(this.tresCompras.calcula("clienteName")).thenReturn(0.2);
      double desconto = this.calculaDescontoCase.calcula("clienteName");
      Assertions.assertEquals(0.2, desconto);
   }
}