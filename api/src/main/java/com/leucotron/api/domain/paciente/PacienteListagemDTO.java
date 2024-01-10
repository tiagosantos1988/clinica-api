package com.leucotron.api.domain.paciente;

import com.leucotron.api.domain.paciente.Paciente;

public record PacienteListagemDTO(Long id, String nome, String email, String cpf) {
    public PacienteListagemDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
