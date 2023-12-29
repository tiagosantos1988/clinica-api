alter table medico add ativo tinyint;
update medico set ativo = 1;
alter table medico MODIFY ativo tinyint NOT NULL;