package com.pucrs.modulovendas;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.data.jb.jpa.JpaOrcamentoImp;
import com.pucrs.modulovendas.data.jb.jpa.OrcamentoRepoImp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class OrcamentoRepoImpTest {
   @InjectMocks
   private OrcamentoRepoImp orcamentoRepoImp;
   @Mock
   private JpaOrcamentoImp jpaOrcamentoImp;

   OrcamentoRepoImpTest() {
   }

   @Test
   void testFindByCod() {
      Long cod = 1L;
      Orcamento orcamento = new Orcamento();
      Mockito.when(this.jpaOrcamentoImp.findBycod(cod)).thenReturn(Optional.of(orcamento));
      Optional<Orcamento> result = this.orcamentoRepoImp.findByCod(cod);
      Assertions.assertEquals(Optional.of(orcamento), result);
      ((JpaOrcamentoImp)Mockito.verify(this.jpaOrcamentoImp, Mockito.times(1))).findBycod(cod);
   }

   @Test
   void testFindAll() {
      List<Orcamento> orcamentos = new ArrayList();
      Mockito.when(this.jpaOrcamentoImp.findAll()).thenReturn(orcamentos);
      List<Orcamento> result = this.orcamentoRepoImp.findAll();
      Assertions.assertEquals(orcamentos, result);
      ((JpaOrcamentoImp)Mockito.verify(this.jpaOrcamentoImp, Mockito.times(1))).findAll();
   }

   @Test
   void testPersist() {
      Orcamento orcamento = new Orcamento();
      Mockito.when((Orcamento)this.jpaOrcamentoImp.save(orcamento)).thenReturn(orcamento);
      Orcamento result = this.orcamentoRepoImp.persist(orcamento);
      Assertions.assertEquals(orcamento, result);
      ((JpaOrcamentoImp)Mockito.verify(this.jpaOrcamentoImp, Mockito.times(1))).save(orcamento);
   }

   @Test
   void testFindByNomeCliente() {
      String nomeCliente = "Teste";
      List<Orcamento> orcamentos = new ArrayList();
      Mockito.when(this.jpaOrcamentoImp.findBynomeCliente(nomeCliente)).thenReturn(orcamentos);
      List<Orcamento> result = this.orcamentoRepoImp.findBynomeCliente(nomeCliente);
      Assertions.assertEquals(orcamentos, result);
      ((JpaOrcamentoImp)Mockito.verify(this.jpaOrcamentoImp, Mockito.times(1))).findBynomeCliente(nomeCliente);
   }
}