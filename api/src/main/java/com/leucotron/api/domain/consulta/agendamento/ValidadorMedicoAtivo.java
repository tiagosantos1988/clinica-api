package com.leucotron.api.domain.consulta.agendamento;

import com.leucotron.api.domain.consulta.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO){
        if(agendamentoConsultaDTO.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(agendamentoConsultaDTO.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com um médico inativo!");
        }
    }
}
