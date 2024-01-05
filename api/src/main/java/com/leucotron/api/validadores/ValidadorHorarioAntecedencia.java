package com.leucotron.api.validadores;

import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas{
    public void validar(AgendamentoConsultaDTO consultaDTO){
        var dataConsulta = consultaDTO.data();
        var dataAgora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();
        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
