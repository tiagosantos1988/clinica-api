package com.leucotron.api.service;

import com.leucotron.api.entity.Consulta;
import com.leucotron.api.entity.Medico;
import com.leucotron.api.entity.dto.AgendamentoConsultaDTO;
import com.leucotron.api.entity.dto.CancelamentoConsultaDTO;
import com.leucotron.api.entity.dto.ConsultaDetalhamentoDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.repositories.ConsultaRepository;
import com.leucotron.api.repositories.MedicoRepository;
import com.leucotron.api.repositories.PacienteRepository;
import com.leucotron.api.validadores.agendamento.ValidadorAgendamentoDeConsultas;
import com.leucotron.api.validadores.cancelamento.ValidadorCancelamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadoresAgendamento;

    @Autowired
    private List<ValidadorCancelamentoDeConsultas> validadoresCancelamnto;

    public ConsultaDetalhamentoDTO agendar(AgendamentoConsultaDTO agendamentoDTO) {

        if (!pacienteRepository.existsById(agendamentoDTO.idPaciente())) {
            throw new ValidacaoException("Não existe paciente com o ID informado!");
        }

        if (agendamentoDTO.idMedico() != null && !medicoRepository.existsById(agendamentoDTO.idMedico())) {
            throw new ValidacaoException("Não existe medico com o ID informado!");
        }

        validadoresAgendamento.forEach(v -> v.validar(agendamentoDTO));

        var medico = escolherMedico(agendamentoDTO);
        var paciente = pacienteRepository.getReferenceById(agendamentoDTO.idPaciente());

        var consulta = new Consulta(null, medico, paciente, agendamentoDTO.data(), null);

        consultaRepository.save(consulta);

        return new ConsultaDetalhamentoDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO agendamentoDTO) {
        if (agendamentoDTO.idMedico() != null) {
            return medicoRepository.getReferenceById(agendamentoDTO.idMedico());
        }

        if (agendamentoDTO.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não foi informado!");
        }

        var medicoEscolhido = medicoRepository.escolheMedicoAleatorioLivreNaData(agendamentoDTO.especialidade(), agendamentoDTO.data());
        if (medicoEscolhido == null) {
            throw new ValidacaoException("Não foi encontrado médico com essa especialidade no horario escolhido!");
        }

        return medicoEscolhido;
    }

    public void cancelar(CancelamentoConsultaDTO cancelamentoConsultaDTO) {
        if (!consultaRepository.existsById(cancelamentoConsultaDTO.idConsulta())) {
            throw new ValidacaoException("Não existe consulta agendada com o ID informado!");
        }

        validadoresCancelamnto.forEach(v -> v.validar(cancelamentoConsultaDTO));

        var consulta = consultaRepository.getReferenceById(cancelamentoConsultaDTO.idConsulta());
        consulta.cancelaAgendamento(cancelamentoConsultaDTO.motivoCancelamento());
    }
}
