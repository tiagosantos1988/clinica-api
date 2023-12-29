package com.leucotron.api.entity.dto;


import com.leucotron.api.entity.Endereco;
import com.leucotron.api.entity.Paciente;

public record PacienteDetalhamentoDTO(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public PacienteDetalhamentoDTO (Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
