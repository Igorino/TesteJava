package com.test.controller;

import com.test.entities.Transferencia;
import com.test.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
        Transferencia agendada = transferenciaService.agendarTransferencia(transferencia);
        return ResponseEntity.ok(agendada);
    }

    @GetMapping
    public List<Transferencia> listarAgendamentos() {
        return transferenciaService.listarAgendamentos();
    }
}
