package com.leucotron.api.validadores;

import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteComOutraConsultaNoDia implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO consultaDTO){
        var primeiroHorario = consultaDTO.data().withHour(7);
        var ultimoHorario = consultaDTO.data().withHour(18);

        var pacientePossuiOutraConsultaNoMesmoDia = consultaRepository.existsByPacienteIdAndDataBetween(consultaDTO.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoMesmoDia){
            throw new ValidacaoException("Paciente já possui outra consulta marcada no mesmo dia!");
        }
    }
}
