package com.leucotron.api.controller;

import com.leucotron.api.domain.medico.Medico;
import com.leucotron.api.domain.medico.MedicoCadastroDTO;
import com.leucotron.api.domain.medico.MedicoDetalhamentoDTO;
import com.leucotron.api.domain.medico.MedicoListagemDTO;
import com.leucotron.api.domain.medico.MedicoUpdateDTO;
import com.leucotron.api.domain.medico.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoCadastroDTO medicoDTO, UriComponentsBuilder uriBuilder){
        var medico = repository.save(new Medico(medicoDTO));

        var uri = uriBuilder.path("medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetalhamentoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@Valid @RequestBody MedicoUpdateDTO dto){
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarCadastro(dto);

        return ResponseEntity.ok(new MedicoDetalhamentoDTO(medico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoDetalhamentoDTO(medico));
    }

    @GetMapping("listarTodos")
    public List<MedicoListagemDTO> listarTodos(){
        return repository.findAll().stream().map(MedicoListagemDTO::new).toList();
    }

//    @DeleteMapping("{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        repository.deleteById(id);
//    }
}
