package br.com.coruja.application.model.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.coruja.application.model.entities.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

}
