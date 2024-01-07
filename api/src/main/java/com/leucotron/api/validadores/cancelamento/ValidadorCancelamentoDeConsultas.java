package com.leucotron.api.validadores.cancelamento;

import com.leucotron.api.entity.dto.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsultas {
    void validar(CancelamentoConsultaDTO cancelamentoDTO);
}
