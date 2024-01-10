package com.leucotron.api.domain.consulta.agendamento;

import com.leucotron.api.domain.consulta.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadoPacienteAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(AgendamentoConsultaDTO consultaDTO) {

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(consultaDTO.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada por um paciente inativo!");
        }
    }
}
