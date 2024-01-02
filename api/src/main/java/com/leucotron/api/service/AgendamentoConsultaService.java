package com.leucotron.api.service;

import com.leucotron.api.entity.Consulta;
import com.leucotron.api.entity.Medico;
import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.repositories.ConsultaRepository;
import com.leucotron.api.repositories.MedicoRepository;
import com.leucotron.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agenda(AgendamentoConsultaDTO agendamentoDTO) {

        if (!pacienteRepository.existsById(agendamentoDTO.idPaciente())) {
            throw new ValidacaoException("Não existe paciente com o ID informado!");
        }

        if (agendamentoDTO.idMedico() != null && !medicoRepository.existsById(agendamentoDTO.idMedico())) {
            throw new ValidacaoException("Não existe medico com o ID informado!");
        }

        var medico = escolherMedico(agendamentoDTO);
        var paciente = pacienteRepository.getReferenceById(agendamentoDTO.idPaciente());

        consultaRepository.save(new Consulta(null, medico, paciente, agendamentoDTO.data()));

    }

    private Medico escolherMedico(AgendamentoConsultaDTO agendamentoDTO) {
        if (agendamentoDTO.idMedico() != null) {
            return medicoRepository.getReferenceById(agendamentoDTO.idMedico());
        }

        if (agendamentoDTO.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não foi informado!");
        }

        return medicoRepository.escolheMedicoAleatorioLivreNaData(agendamentoDTO.especialidade(), agendamentoDTO.data());
    }
}
