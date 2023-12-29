package com.leucotron.api.controller;

import com.leucotron.api.entity.Paciente;
import com.leucotron.api.entity.dto.PacienteCadastroDTO;
import com.leucotron.api.entity.dto.PacienteDetalhamentoDTO;
import com.leucotron.api.entity.dto.PacienteListagemDTO;
import com.leucotron.api.entity.dto.PacienteUpdateDTO;
import com.leucotron.api.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
public class PacienteController {

   @Autowired
    private PacienteRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteCadastroDTO pacienteDTO, UriComponentsBuilder uriBuilder){
        var paciente = repository.save(new Paciente(pacienteDTO));

        var uri = uriBuilder.path("paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDetalhamentoDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListagemDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(PacienteListagemDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PacienteUpdateDTO dto){
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizar(dto);

        return ResponseEntity.ok(new PacienteDetalhamentoDTO(paciente));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.desativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new PacienteDetalhamentoDTO(paciente));
    }
}
