package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.enums.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO (
        Long idMedico,

        Especialidade especialidade,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data
){}
