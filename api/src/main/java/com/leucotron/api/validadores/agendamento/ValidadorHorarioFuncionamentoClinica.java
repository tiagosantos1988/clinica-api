package com.leucotron.api.validadores.agendamento;

import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{
    public void validar(AgendamentoConsultaDTO consultaDTO){
        var data = consultaDTO.data();

        if(data.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ValidacaoException("Consulta não pode ser agendada para um Domingo!");
        }

        if(data.getHour() < 7 || data.getHour() > 18){
            throw new ValidacaoException("Consulta não pode ser agendada fora do horário de funcionamento da clínica!");
        }
    }
}
