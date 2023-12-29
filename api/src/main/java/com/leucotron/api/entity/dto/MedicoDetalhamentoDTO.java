package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.Endereco;
import com.leucotron.api.entity.Medico;
import com.leucotron.api.entity.enums.Especialidade;

public record MedicoDetalhamentoDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
    public MedicoDetalhamentoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
