package br.com.coruja.application.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coruja.application.model.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
