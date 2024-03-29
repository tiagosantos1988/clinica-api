package com.leucotron.api.domain.consulta.agendamento;

import com.leucotron.api.domain.consulta.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO consultaDTO){
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(consultaDTO.idMedico(), consultaDTO.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico informado já possui outra consulta nesse mesmo horário!");
        }
    }
}
