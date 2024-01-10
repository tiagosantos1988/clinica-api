package com.leucotron.api.domain.medico;

import com.leucotron.api.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDTO(@NotNull Long id, String nome, String email, EnderecoDTO endereco) {
}
