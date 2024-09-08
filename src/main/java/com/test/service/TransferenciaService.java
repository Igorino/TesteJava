package com.test.service;

import com.test.entities.Transferencia;
import com.test.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        // Lógica para calcular a taxa com base na data de transferência
        BigDecimal taxa = calcularTaxa(transferencia);
        transferencia.setTaxa(taxa);
        return transferenciaRepository.save(transferencia);
    }

    protected BigDecimal calcularTaxa(Transferencia transferencia) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataTransferencia = transferencia.getDataTransferencia();

        long diasEntre = ChronoUnit.DAYS.between(hoje, dataTransferencia);
        BigDecimal valorTransferencia = transferencia.getValor();
        BigDecimal taxa;

        if (diasEntre == 0) {
            // Taxa de 3,00 + 2,5% do valor
            taxa = new BigDecimal("3.00").add(valorTransferencia.multiply(new BigDecimal("0.025")));
        } else if (diasEntre >= 1 && diasEntre <= 10) {
            // Taxa de 12,00
            taxa = new BigDecimal("12.00");
        } else if (diasEntre >= 11 && diasEntre <= 20) {
            // Taxa de 8,2% do valor
            taxa = valorTransferencia.multiply(new BigDecimal("0.082"));
        } else if (diasEntre >= 21 && diasEntre <= 30) {
            // Taxa de 6,9% do valor
            taxa = valorTransferencia.multiply(new BigDecimal("0.069"));
        } else if (diasEntre >= 31 && diasEntre <= 40) {
            // Taxa de 4,7% do valor
            taxa = valorTransferencia.multiply(new BigDecimal("0.047"));
        } else if (diasEntre >= 41 && diasEntre <= 50) {
            // Taxa de 1,7% do valor
            taxa = valorTransferencia.multiply(new BigDecimal("0.017"));
        } else {
            // Caso a data de transferência esteja fora do intervalo permitido, lançar uma exceção
            throw new IllegalArgumentException("Data de transferência fora do intervalo permitido (0-50 dias).");
        }

        return taxa.setScale(2, BigDecimal.ROUND_HALF_UP); // Arredonda para 2 casas decimais
    }

    public List<Transferencia> listarAgendamentos() {
        return transferenciaRepository.findAll();
    }
}
