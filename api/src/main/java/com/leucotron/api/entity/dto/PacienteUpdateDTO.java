package com.leucotron.api.entity.dto;

import com.leucotron.api.entity.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record PacienteUpdateDTO(@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {
}
