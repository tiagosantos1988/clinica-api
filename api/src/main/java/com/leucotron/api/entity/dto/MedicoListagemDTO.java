package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.Medico;
import com.leucotron.api.entity.enums.Especialidade;

public record MedicoListagemDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public MedicoListagemDTO (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
