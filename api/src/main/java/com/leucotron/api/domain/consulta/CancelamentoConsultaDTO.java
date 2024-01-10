package com.leucotron.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento
){}
