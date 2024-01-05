package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public ConsultaDetalhamentoDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
