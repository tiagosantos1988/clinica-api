package com.leucotron.api.controller;

import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.entity.dto.CancelamentoConsultaDTO;
import com.leucotron.api.service.AgendamentoConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    private AgendamentoConsultaService agendamentoConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamentoDTO) {

        var consultaDetalhamentoDTO = agendamentoConsultaService.agendar(agendamentoDTO);

        return ResponseEntity.ok(consultaDetalhamentoDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO cancelamentoConsultaDTO) {

        agendamentoConsultaService.cancelar(cancelamentoConsultaDTO);

        return ResponseEntity.noContent().build();
    }
}
