package br.com.coruja.application.model.repositories;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.coruja.application.model.entities.Aluno;

public class AlunoRepository extends SimpleJpaRepository<Aluno, Integer> {

    public AlunoRepository(Class<Aluno> domainClass, EntityManager em) {
        super(domainClass, em);
        // TODO Auto-generated constructor stub
    }

}
