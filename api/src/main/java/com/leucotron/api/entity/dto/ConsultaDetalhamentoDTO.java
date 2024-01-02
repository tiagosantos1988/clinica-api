package com.leucotron.api.entity.dto;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
