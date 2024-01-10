package com.leucotron.api.domain.medico;

import com.leucotron.api.domain.endereco.Endereco;

public record MedicoDetalhamentoDTO(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
    public MedicoDetalhamentoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
