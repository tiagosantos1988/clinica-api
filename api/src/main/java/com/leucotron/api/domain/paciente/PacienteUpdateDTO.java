package com.leucotron.api.domain.paciente;

import com.leucotron.api.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record PacienteUpdateDTO(@NotNull Long id, String nome, String telefone, EnderecoDTO endereco) {
}
