package com.leucotron.api.controller;

import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.entity.dto.CancelamentoConsultaDTO;
import com.leucotron.api.entity.dto.ConsultaDetalhamentoDTO;
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

        agendamentoConsultaService.agenda(agendamentoDTO);

        return ResponseEntity.ok(new ConsultaDetalhamentoDTO(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO cancelamentoConsultaDTO) {

        agendamentoConsultaService.cancelamento(cancelamentoConsultaDTO);

        return ResponseEntity.noContent().build();
    }
}
