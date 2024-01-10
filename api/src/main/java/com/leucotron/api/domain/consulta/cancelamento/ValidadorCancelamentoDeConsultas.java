package com.leucotron.api.domain.consulta.cancelamento;

import com.leucotron.api.domain.consulta.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsultas {
    void validar(CancelamentoConsultaDTO cancelamentoDTO);
}
