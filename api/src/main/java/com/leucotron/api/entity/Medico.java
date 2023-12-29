package com.leucotron.api.entity;

import com.leucotron.api.entity.dto.MedicoCadastroDTO;
import com.leucotron.api.entity.dto.MedicoUpdateDTO;
import com.leucotron.api.entity.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(MedicoCadastroDTO dto) {
        this.ativo = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.crm = dto.crm();
        this.telefone = dto.telefone();
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizarCadastro(MedicoUpdateDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.email() != null){
            this.email = dto.email();
        }
        if(dto.endereco() != null){
            this.endereco = new Endereco(dto.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
