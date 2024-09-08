package com.test.service;

import com.test.entities.Transferencia;
import com.test.repository.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    private Transferencia transferencia;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transferencia = new Transferencia();
        transferencia.setContaOrigem("1234567890");
        transferencia.setContaDestino("0987654321");
        transferencia.setValor(new BigDecimal("1000.00"));
        transferencia.setDataAgendamento(LocalDate.now());
    }

    @Test
    public void calcularTaxa_dia0() {
        transferencia.setDataTransferencia(LocalDate.now());
        BigDecimal taxaEsperada = new BigDecimal("28.00"); // 3.00 + (2.5% de 1000 = 25.00)
        BigDecimal taxaCalculada = transferenciaService.calcularTaxa(transferencia);

        assertEquals(taxaEsperada, taxaCalculada);
    }

    @Test
    public void calcularTaxa_entre1e10dias() {
        transferencia.setDataTransferencia(LocalDate.now().plusDays(5));
        BigDecimal taxaEsperada = new BigDecimal("12.00");
        BigDecimal taxaCalculada = transferenciaService.calcularTaxa(transferencia);

        assertEquals(taxaEsperada, taxaCalculada);
    }

    @Test
    public void calcularTaxa_entre11e20dias() {
        transferencia.setDataTransferencia(LocalDate.now().plusDays(15));
        BigDecimal taxaEsperada = new BigDecimal("82.00"); // 8.2% de 1000
        BigDecimal taxaCalculada = transferenciaService.calcularTaxa(transferencia);

        assertEquals(taxaEsperada, taxaCalculada);
    }

    @Test
    public void calcularTaxa_entre21e30dias() {
        transferencia.setDataTransferencia(LocalDate.now().plusDays(25));
        BigDecimal taxaEsperada = new BigDecimal("69.00"); // 6.9% de 1000
        BigDecimal taxaCalculada = transferenciaService.calcularTaxa(transferencia);

        assertEquals(taxaEsperada, taxaCalculada);
    }

    @Test
    public void calcularTaxa_entre31e40dias() {
        transferencia.setDataTransferencia(LocalDate.now().plusDays(35));
        BigDecimal taxaEsperada = new BigDecimal("47.00"); // 4.7% de 1000
        BigDecimal taxaCalculada = transferenciaService.calcularTaxa(transferencia);

        assertEquals(taxaEsperada, taxaCalculada);
    }

    @Test
    public void calcularTaxa_entre41e50dias() {
        transferencia.setDataTransferencia(LocalDate.now().plusDays(45));
        BigDecimal taxaEsperada = new BigDecimal("17.00"); // 1.7% de 1000
        BigDecimal taxaCalculada = transferenciaService.calcularTaxa(transferencia);

        assertEquals(taxaEsperada, taxaCalculada);
    }

    @Test
    public void calcularTaxa_acimaDe50dias() {
        transferencia.setDataTransferencia(LocalDate.now().plusDays(55));
        assertThrows(IllegalArgumentException.class, () -> transferenciaService.calcularTaxa(transferencia));
    }
}
