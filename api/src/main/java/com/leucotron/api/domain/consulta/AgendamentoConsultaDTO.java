package com.leucotron.api.domain.consulta;

import com.leucotron.api.domain.medico.Especialidade;
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
