package com.leucotron.api.domain.paciente;


import com.leucotron.api.domain.endereco.Endereco;
import com.leucotron.api.domain.paciente.Paciente;

public record PacienteDetalhamentoDTO(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public PacienteDetalhamentoDTO (Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
