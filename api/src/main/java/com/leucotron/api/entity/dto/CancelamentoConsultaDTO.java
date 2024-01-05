package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento
){}
