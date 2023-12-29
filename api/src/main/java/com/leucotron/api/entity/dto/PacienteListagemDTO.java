package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.Paciente;

public record PacienteListagemDTO(Long id, String nome, String email, String cpf) {
    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
