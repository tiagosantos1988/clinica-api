package com.leucotron.api.repositories;

import com.leucotron.api.entity.Medico;
import com.leucotron.api.entity.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medico m
            where m.ativo = true
            and m.especialidade = :especialidade
            and m.id not in(
                select c.medico.id from Consulta c
                where c.data = :data
                and c.motivoCancelamento is null
            )   
            order by rand()
            limit 1
            """)
    Medico escolheMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo from Medico m
            where m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
