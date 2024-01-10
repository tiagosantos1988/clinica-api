package com.leucotron.api.domain.consulta;

import com.leucotron.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public ConsultaDetalhamentoDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
