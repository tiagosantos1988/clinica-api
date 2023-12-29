alter table paciente add ativo tinyint;
update paciente set ativo = 1;
alter table paciente MODIFY ativo tinyint NOT NULL;