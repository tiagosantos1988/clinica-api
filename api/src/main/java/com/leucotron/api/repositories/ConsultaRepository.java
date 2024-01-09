package com.leucotron.api.repositories;

import com.leucotron.api.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long medicoId, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
