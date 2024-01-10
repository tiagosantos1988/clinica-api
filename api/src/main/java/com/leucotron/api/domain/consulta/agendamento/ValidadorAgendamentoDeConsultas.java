package com.leucotron.api.domain.consulta.agendamento;

import com.leucotron.api.domain.consulta.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsultas {
    void validar(AgendamentoConsultaDTO consultaDTO);
}
