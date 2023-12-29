package com.leucotron.api.entity;

import com.leucotron.api.entity.dto.PacienteUpdateDTO;
import com.leucotron.api.entity.dto.PacienteCadastroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(PacienteCadastroDTO dto) {
        this.ativo = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizar(PacienteUpdateDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
        if(dto.endereco() != null){
            this.endereco = new Endereco(dto.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
