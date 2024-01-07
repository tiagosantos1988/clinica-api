package com.leucotron.api.validadores.cancelamento;

import com.leucotron.api.entity.dto.CancelamentoConsultaDTO;
import com.leucotron.api.infra.exceptions.ValidacaoException;
import com.leucotron.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Validador24HorasAntecedencia implements ValidadorCancelamentoDeConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(CancelamentoConsultaDTO cancelamentoDTO) {
        var horaConsulta = consultaRepository.getReferenceById(cancelamentoDTO.idConsulta()).getData();
        var horaAgora = LocalDateTime.now();

        if(Duration.between(horaAgora, horaConsulta).toHours() < 24){
            throw new ValidacaoException("Consulta só pode ser cancelada com mais de 24 horas de antecedência!");
        }
    }
}
